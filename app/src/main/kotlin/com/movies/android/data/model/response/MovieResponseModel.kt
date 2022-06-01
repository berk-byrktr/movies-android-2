package com.movies.android.data.model.response

import com.movies.android.common.network.ResponseModel

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

data class MovieResponseModel(
    val adult: Boolean?,
    val backdrop_path: String?,
    val id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: Int?,
    val genres_ids: List<Int>?
) : ResponseModel