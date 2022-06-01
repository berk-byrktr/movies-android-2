package com.movies.android

import com.movies.android.di.component.CoreComponent
import com.movies.android.di.component.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class App : DaggerApplication() {

    @Inject
    internal lateinit var injector: DispatchingAndroidInjector<Any>

    private var coreComponent: CoreComponent? = null

    init {
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        getCoreComponent()

    fun getCoreComponent(): CoreComponent {
        if (coreComponent == null) {
            coreComponent = DaggerCoreComponent.builder().app(this).build()
            coreComponent?.inject(this)
        }
        return coreComponent!!
    }

    companion object {
        lateinit var instance: App private set
    }
}