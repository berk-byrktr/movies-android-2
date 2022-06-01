package com.movies.android.common.extension

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

fun Any?.runIfNotNull(block: () -> Unit) {
    if (this != null) block()
}