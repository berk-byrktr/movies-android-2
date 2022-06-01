package com.movies.android.di.annotation

import com.movies.android.common.network.NetworkComponentType
import javax.inject.Qualifier

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkKey(val value: NetworkComponentType)
