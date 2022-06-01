package com.movies.android.feature.splash

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

sealed class SplashEvent {
    object GoToHome : SplashEvent()
    object Idle : SplashEvent()
}