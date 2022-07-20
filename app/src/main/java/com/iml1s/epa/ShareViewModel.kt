package com.iml1s.epa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class ShareViewModel : ViewModel() {

    val searchViewQueryText = MutableStateFlow("")
    val isNavigationBackButtonShow = MutableStateFlow(false)

    init {
        searchViewQueryText.value = "hello world"
        searchViewQueryText.onEach {
            Timber.tag(TIMBER_DEBUG_TAG).d(it)
        }.launchIn(viewModelScope)
    }
}