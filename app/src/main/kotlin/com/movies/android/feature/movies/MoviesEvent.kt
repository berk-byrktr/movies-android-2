package com.movies.android.feature.movies

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

sealed class MoviesEvent {
    object GetPopularMovies : MoviesEvent()
    object Idle : MoviesEvent()
}