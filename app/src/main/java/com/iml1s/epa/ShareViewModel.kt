package com.iml1s.epa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class ShareViewModel : ViewModel() {

    val searchViewQueryText = MutableStateFlow("")

    private val _isNavigationBackButtonShow = MutableStateFlow(false)
    val isNavigationBackButtonShow = _isNavigationBackButtonShow.asStateFlow()

    private val _onSearchClose = MutableSharedFlow<Unit>()
    val onSearchCloseSource = _onSearchClose.asSharedFlow()

    init {
        searchViewQueryText.onEach {
            Timber.tag(TIMBER_DEBUG_TAG).d(it)
        }.launchIn(viewModelScope)

        onSearchCloseSource.onEach {
            _isNavigationBackButtonShow.value = false
        }.launchIn(viewModelScope)
    }

    fun showBackButton(isShow: Boolean) {
        _isNavigationBackButtonShow.value = isShow
    }

    fun onSearchClick() {
        _isNavigationBackButtonShow.value = true
    }

    fun onSearchClose() = viewModelScope.launch {
        _onSearchClose.emit(Unit)
    }.run { Unit }
}