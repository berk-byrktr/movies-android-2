package com.movies.android.common.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

inline fun <T : ViewBinding> ViewGroup.viewBinding(
    viewBindingFactory:
        (LayoutInflater, ViewGroup, Boolean) -> T
) = viewBindingFactory.invoke(LayoutInflater.from(this.context), this, false)