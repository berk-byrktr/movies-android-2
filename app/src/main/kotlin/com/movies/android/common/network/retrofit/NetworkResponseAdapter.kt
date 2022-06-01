package com.movies.android.common.network.retrofit

import com.movies.android.common.network.NetworkResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class NetworkResponseAdapter<T : Any>(
    private val successType: Type,
) : CallAdapter<T, Call<NetworkResponse<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<NetworkResponse<T>> =
        NetworkResponseCall(call)
}