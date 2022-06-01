package com.movies.android.common.base

/**
 * Created by Berk Bayraktar on 27.04.2022
 */

data class BaseResponse<T>(
    val results: T?,
    val page: Int?,
    val total_pages: Int?,
    val total_results: Int?
)