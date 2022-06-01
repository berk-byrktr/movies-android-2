package com.movies.android.data.repository.movie

import com.movies.android.common.base.BaseResponse
import com.movies.android.common.network.Resource
import com.movies.android.data.model.request.PopularMoviesBody
import com.movies.android.data.model.response.MovieResponseModel

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

interface MovieRepository {

    suspend fun getPopularMovies(moviesBody: PopularMoviesBody): Resource<BaseResponse<List<MovieResponseModel>>>
}