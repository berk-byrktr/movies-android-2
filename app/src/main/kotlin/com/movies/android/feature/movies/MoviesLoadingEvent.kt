package com.movies.android.feature.movies

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

sealed class MoviesLoadingEvent {
    object Loading : MoviesLoadingEvent()
    object Idle : MoviesLoadingEvent()
}