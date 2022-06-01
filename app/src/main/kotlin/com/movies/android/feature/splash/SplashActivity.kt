package com.movies.android.feature.splash

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.movies.android.App
import com.movies.android.common.base.BaseActivity
import com.movies.android.common.base.BaseViewModel
import com.movies.android.di.component.DaggerSplashComponent
import com.movies.android.feature.movies.MoviesActivity
import kotlinx.coroutines.flow.collect

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class SplashActivity : BaseActivity() {

    private val vm: SplashViewModel by viewModels { viewModelFactory }

    override val viewModel: BaseViewModel get() = vm

    override fun initDI() {
        DaggerSplashComponent.builder()
            .coreComponent(App.instance.getCoreComponent())
            .build()
            .inject(this)
    }

    override fun bindView() {
        //DO Nothing
    }

    override fun onCreate() {
        initObservers()
        vm.goToHome()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            vm.splashFlow.collect {
                when (it) {
                    SplashEvent.GoToHome -> startHomePage()
                }
            }
        }
    }

    private fun startHomePage() {
        startActivity(MoviesActivity.createStartActivityIntent(this))
        finish()
    }
}