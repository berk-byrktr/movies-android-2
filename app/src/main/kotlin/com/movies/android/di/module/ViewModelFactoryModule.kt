package com.movies.android.di.module

import androidx.lifecycle.ViewModelProvider
import com.movies.android.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module


/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}