package com.movies.android.common.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

fun Context.getCompatColor(@ColorRes colorInt: Int): Int = ContextCompat.getColor(this, colorInt)