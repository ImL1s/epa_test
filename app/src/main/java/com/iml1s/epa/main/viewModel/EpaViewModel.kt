package com.iml1s.epa.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iml1s.epa.TIMBER_DEBUG_TAG
import com.iml1s.epa.main.model.AirData
import com.iml1s.epa.main.model.Record
import com.iml1s.epa.main.repository.EpaRepository
import kotlinx.coroutines.flow.*
import timber.log.Timber

class EpaViewModel(private val epaRepository: EpaRepository) : ViewModel() {

    private val _goodAirQualityList = MutableStateFlow<List<AirData>>(emptyList())
    val goodAirQualityList = _goodAirQualityList.asStateFlow()

    init {
        epaRepository.getEpaData()
            .map { it.records }
            .onEach {
                Timber.tag(TIMBER_DEBUG_TAG).d(it.toString())
                _goodAirQualityList.value = it.map { rawData -> createAirData(rawData) }
            }
            .catch { Timber.tag(TIMBER_DEBUG_TAG).e(it) }
            .launchIn(viewModelScope)
    }

    private fun createAirData(rawData: Record) = rawData.run {
        AirData(siteID, siteName, county, pm25, status)
    }
}