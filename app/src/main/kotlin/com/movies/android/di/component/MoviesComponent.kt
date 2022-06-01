package com.movies.android.di.component

import com.movies.android.common.base.BaseActivityComponent
import com.movies.android.di.module.MoviesViewModelModule
import com.movies.android.di.module.ViewModelFactoryModule
import com.movies.android.di.scope.ActivityScope
import com.movies.android.feature.movies.MoviesActivity
import dagger.Component

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Component(
    modules = [MoviesViewModelModule::class, ViewModelFactoryModule::class],
    dependencies = [CoreComponent::class]
)
@ActivityScope
interface MoviesComponent : BaseActivityComponent<MoviesActivity> {
    @Component.Builder
    interface Builder {
        fun coreComponent(component: CoreComponent): Builder
        fun build(): MoviesComponent
    }
}