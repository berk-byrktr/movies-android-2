package com.movies.android.common.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

abstract class BaseFlowUseCase<in Params, Type> constructor(
    private val dispatcher: CoroutineDispatcher
) {
    abstract suspend fun getExecutable(params: Params): Flow<Type>

    suspend operator fun invoke(params: Params): Flow<Type> {
        return withContext(dispatcher) {
            getExecutable(params)
        }
    }
}