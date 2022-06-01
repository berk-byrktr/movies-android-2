package com.movies.android.common.network

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    object Failure : Resource<Nothing>()
}
