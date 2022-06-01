package com.movies.android.common.network

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

sealed class NetworkResponse<T> {
    data class Success<T>(val body: T) : NetworkResponse<T>()
    class Failure<T>() : NetworkResponse<T>()
}