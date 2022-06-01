package com.movies.android.data.remote.datasource

import com.movies.android.common.base.BaseResponse
import com.movies.android.common.extension.getResponseResource
import com.movies.android.common.network.NetworkComponentType
import com.movies.android.common.network.Resource
import com.movies.android.data.model.request.PopularMoviesBody
import com.movies.android.data.model.response.MovieResponseModel
import com.movies.android.data.remote.api.ApiService
import com.movies.android.di.annotation.NetworkKey
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class RemoteDataSourceImpl @Inject constructor(
    @NetworkKey(NetworkComponentType.API_SERVICE)
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getPopularMovies(moviesBody: PopularMoviesBody): Resource<BaseResponse<List<MovieResponseModel>>> =
        apiService.getPopularMovies(moviesBody.language, moviesBody.page).getResponseResource()
}