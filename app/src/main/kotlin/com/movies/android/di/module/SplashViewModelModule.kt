package com.movies.android.di.module

import androidx.lifecycle.ViewModel
import com.movies.android.di.annotation.ViewModelKey
import com.movies.android.feature.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Module
abstract class SplashViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}