package com.movies.android.common.network.retrofit

import com.movies.android.common.network.NetworkResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

internal class NetworkResponseCall<T : Any>(
    private val delegate: Call<T>,
) : Call<NetworkResponse<T>> {

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<NetworkResponse<T>> =
        NetworkResponseCall(delegate.clone())

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<NetworkResponse<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Success(body))
                    )
                } else {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Failure())
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkResponse.Failure())
                )
            }
        })
    }
}