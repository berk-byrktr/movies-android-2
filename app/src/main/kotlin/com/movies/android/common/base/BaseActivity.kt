package com.movies.android.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

abstract class BaseActivity : AppCompatActivity() {

    abstract val viewModel: ViewModel

    abstract fun initDI()

    abstract fun bindView()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    open fun onCreate() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDI()
        bindView()
        onCreate()
    }
}