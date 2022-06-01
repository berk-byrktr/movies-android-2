package com.movies.android.data.repository.movie

import com.movies.android.common.base.BaseResponse
import com.movies.android.common.network.Resource
import com.movies.android.data.model.request.PopularMoviesBody
import com.movies.android.data.model.response.MovieResponseModel
import com.movies.android.data.remote.datasource.RemoteDataSource
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(moviesBody: PopularMoviesBody): Resource<BaseResponse<List<MovieResponseModel>>> =
        remoteDataSource.getPopularMovies(moviesBody)
}