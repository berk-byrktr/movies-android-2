package com.movies.android.domain.decider

import com.movies.android.R
import com.movies.android.data.model.response.MovieResponseModel
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class MovieDecider @Inject constructor() {

    fun provideImageUrl(movieResponse: MovieResponseModel?) =
        movieResponse?.poster_path?.let {
            return@let "https://image.tmdb.org/t/p/w500" + movieResponse.poster_path
        } ?: ""

    fun provideProgress(movieResponse: MovieResponseModel?) =
        (movieResponse?.vote_average?.times(10) ?: 0).toInt()

    fun provideProgressColor(movieResponse: MovieResponseModel?): Int {
        val rate = (movieResponse?.vote_average?.times(10) ?: 0).toInt()
        return if (rate >= 50) R.color.green else R.color.yellow
    }
}