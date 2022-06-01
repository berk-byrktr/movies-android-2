package com.movies.android.di.module

import com.movies.android.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApp(): App = App.instance
}
