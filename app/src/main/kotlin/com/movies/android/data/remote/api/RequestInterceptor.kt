package com.movies.android.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY_QUERY, API_KEY_VALUE)
            .build()

        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }

    companion object {
        const val API_KEY_QUERY = "api_key"
        const val API_KEY_VALUE = "55957fcf3ba81b137f8fc01ac5a31fb5"
    }
}
