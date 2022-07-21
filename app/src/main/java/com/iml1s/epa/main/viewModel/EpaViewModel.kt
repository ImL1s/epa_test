package com.iml1s.epa.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iml1s.epa.QUALITY_GOOD_THRESHOLD
import com.iml1s.epa.TIMBER_DEBUG_TAG
import com.iml1s.epa.main.model.AirData
import com.iml1s.epa.main.model.Record
import com.iml1s.epa.main.repository.EpaRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

typealias AirDataList = List<AirData>

class EpaViewModel(private val epaRepository: EpaRepository) : ViewModel() {

    private val airQualityList = mutableListOf<AirData>()

    private val _goodAirQualityList = MutableStateFlow<AirDataList>(emptyList())
    val goodAirQualityList = _goodAirQualityList.asStateFlow()

    private val _badAirQualityList = MutableStateFlow<AirDataList>(emptyList())
    val badAirQualityList = _badAirQualityList.asStateFlow()

    private val _queryAirQualityList = MutableStateFlow<AirDataList>(emptyList())
    val queryAirQualityList = _queryAirQualityList.asStateFlow()

    private val _toastSource = MutableSharedFlow<AirData>()
    val toastSource = _toastSource.asSharedFlow()

    val userQuerySource = MutableSharedFlow<String>()
    val isNavigationBackButtonShow = MutableStateFlow(false)

    init {
        epaRepository.getEpaData()
            .map { it.records }
            .map { it.map { rawData -> createAirData(rawData) } }
            .onEach {
                Timber.tag(TIMBER_DEBUG_TAG).d(it.toString())
                if (airQualityList.isNotEmpty()) airQualityList.clear()
                airQualityList.addAll(it)
                _goodAirQualityList.value = it.filter { item -> isGoodAirQuality(item.quality) }
                _badAirQualityList.value = it.filter { item -> !isGoodAirQuality(item.quality) }
            }
            .catch { Timber.tag(TIMBER_DEBUG_TAG).e(it) }
            .launchIn(viewModelScope)

        userQuerySource.onEach { if (it.isEmpty()) _queryAirQualityList.value = emptyList() }
            .filter { it.isNotEmpty() }
            .map {
                airQualityList.filter { originalData -> isMatchUserKeyword(originalData, it) }
            }
            .onEach { _queryAirQualityList.value = it }
            .launchIn(viewModelScope)

        isNavigationBackButtonShow
            .filter { it.not() }
            .onEach { _queryAirQualityList.value = emptyList() }
            .launchIn(viewModelScope)
    }

    private fun isMatchUserKeyword(
        originalData: AirData,
        it: String
    ) = originalData.run {
        siteName.contains(it, true) || city.contains(it, true) || siteId.contains(it, true)
    }

    private fun createAirData(rawData: Record) = rawData.run {
        AirData(siteID, siteName, county, pm25, status)
    }

    private fun isGoodAirQuality(qualityString: String): Boolean {
        return (qualityString.toIntOrNull() ?: return false) <= QUALITY_GOOD_THRESHOLD
    }

    fun onBadAirItemClick(airData: AirData) {
        viewModelScope.launch {
            _toastSource.emit(airData)
        }
    }
}