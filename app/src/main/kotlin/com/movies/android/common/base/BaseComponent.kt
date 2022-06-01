package com.movies.android.common.base

import android.app.Activity

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

interface BaseComponent<T> {

    fun inject(target: T)
}

interface BaseActivityComponent<T : Activity> : BaseComponent<T>
