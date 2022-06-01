package com.movies.android.di.component

import com.movies.android.App
import com.movies.android.data.remote.datasource.RemoteDataSource
import com.movies.android.data.repository.movie.MovieRepository
import com.movies.android.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Component(
    modules = [
        AndroidInjectionModule::class,
        DispatcherModule::class,
        DataModule::class,
        NetworkModule::class,
    ]
)

@Singleton
interface CoreComponent : AndroidInjector<App> {

    override fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: App): Builder
        fun build(): CoreComponent
    }

    fun provideApp(): App

    fun provideRemoteDataSource(): RemoteDataSource

    fun provideMovieRepository(): MovieRepository

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor

    @DefaultDispatcher
    fun providesDefaultDispatcher(): CoroutineDispatcher

    @IoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher

    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher
}
