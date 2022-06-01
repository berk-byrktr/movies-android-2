package com.movies.android.feature.movies

import com.movies.android.domain.model.movie.MovieUIModel

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

sealed class MoviesClickEvent {
    class DataClicked(val item: MovieUIModel) : MoviesClickEvent()
    object Idle : MoviesClickEvent()
}
