package com.movies.android.feature.splash

import androidx.lifecycle.viewModelScope
import com.movies.android.common.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _splashFlow = MutableStateFlow<SplashEvent>(SplashEvent.Idle)
    val splashFlow: StateFlow<SplashEvent> = _splashFlow

    fun goToHome() {
        viewModelScope.launch {
            _splashFlow.value = SplashEvent.Idle
            _splashFlow.value = SplashEvent.GoToHome
        }
    }
}