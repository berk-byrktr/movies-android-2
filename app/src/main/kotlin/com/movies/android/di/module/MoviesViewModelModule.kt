package com.movies.android.di.module

import androidx.lifecycle.ViewModel
import com.movies.android.di.annotation.ViewModelKey
import com.movies.android.feature.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Module
abstract class MoviesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel
}