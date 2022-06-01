package com.movies.android.common.extension

import com.movies.android.common.base.BaseResponse
import com.movies.android.common.network.NetworkResponse
import com.movies.android.common.network.Resource

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

fun <T> NetworkResponse<BaseResponse<T>>.getResponseResource(): Resource<BaseResponse<T>> {
    return when (this) {
        is NetworkResponse.Success -> Resource.Success(body)
        is NetworkResponse.Failure -> Resource.Failure
    }
}