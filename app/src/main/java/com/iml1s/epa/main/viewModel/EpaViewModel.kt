package com.iml1s.epa.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iml1s.epa.TIMBER_DEBUG_TAG
import com.iml1s.epa.main.repository.EpaRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class EpaViewModel(private val epaRepository: EpaRepository) : ViewModel() {

    init {
        epaRepository.getEpaData()
            .onEach {
                Timber.tag(TIMBER_DEBUG_TAG).d(it.toString())
            }
            .catch { Timber.tag(TIMBER_DEBUG_TAG).e(it) }
            .launchIn(viewModelScope)
    }
}