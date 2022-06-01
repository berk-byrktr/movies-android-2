package com.movies.android.data.remote.api

import com.movies.android.common.base.BaseResponse
import com.movies.android.common.network.NetworkResponse
import com.movies.android.data.model.response.MovieResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): NetworkResponse<BaseResponse<List<MovieResponseModel>>>
}
