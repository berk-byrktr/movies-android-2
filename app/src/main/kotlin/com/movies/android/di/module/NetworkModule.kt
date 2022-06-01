package com.movies.android.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.movies.android.BuildConfig
import com.movies.android.common.network.NetworkComponentType
import com.movies.android.common.network.retrofit.NetworkResponseAdapterFactory
import com.movies.android.data.remote.api.ApiService
import com.movies.android.data.remote.api.RequestInterceptor
import com.movies.android.di.annotation.NetworkKey
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Module
object NetworkModule {

    @Singleton
    @Provides
    @NetworkKey(NetworkComponentType.GSON)
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRequestInterceptor(): RequestInterceptor = RequestInterceptor()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    @NetworkKey(NetworkComponentType.OKHTTP)
    fun provideOkhttp(
        requestInterceptor: RequestInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        if (!httpClientBuilder.interceptors().contains(requestInterceptor)) {
            httpClientBuilder.addInterceptor(requestInterceptor)
        }
        if (BuildConfig.DEBUG && !httpClientBuilder.interceptors()
                .contains(httpLoggingInterceptor)
        ) {
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    @NetworkKey(NetworkComponentType.RETROFIT)
    fun provideRetrofit(
        @NetworkKey(NetworkComponentType.OKHTTP) okHttpClient: OkHttpClient,
        @NetworkKey(NetworkComponentType.GSON) gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.HOST)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @NetworkKey(NetworkComponentType.API_SERVICE)
    fun provideApiService(
        @NetworkKey(NetworkComponentType.RETROFIT) retrofit: Retrofit,
    ): ApiService = retrofit.create(ApiService::class.java)
}