package com.movies.android.di.module

import com.movies.android.common.network.NetworkComponentType
import com.movies.android.data.remote.api.ApiService
import com.movies.android.data.remote.datasource.RemoteDataSource
import com.movies.android.data.remote.datasource.RemoteDataSourceImpl
import com.movies.android.data.repository.movie.MovieRepository
import com.movies.android.data.repository.movie.MovieRepositoryImpl
import com.movies.android.di.annotation.NetworkKey
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        @NetworkKey(NetworkComponentType.API_SERVICE) apiService: ApiService
    ): RemoteDataSource = RemoteDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource
    ): MovieRepository = MovieRepositoryImpl(remoteDataSource)
}