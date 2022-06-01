package com.movies.android.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.movies.android.common.Constants
import com.movies.android.common.base.BaseFlowUseCase
import com.movies.android.data.model.request.PopularMoviesParams
import com.movies.android.data.paging.MoviesPagingSource
import com.movies.android.data.repository.movie.MovieRepository
import com.movies.android.di.module.IoDispatcher
import com.movies.android.domain.mapper.MovieUIMapper
import com.movies.android.domain.model.movie.MoviePage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class PopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val uiMapper: MovieUIMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<PopularMoviesUseCase.Param, PagingData<MoviePage>>(dispatcher) {

    override suspend fun getExecutable(params: Param): Flow<PagingData<MoviePage>> =
        Pager(PagingConfig(pageSize = Constants.Pagination.DEFAULT_PAGE_SIZE)) {
            MoviesPagingSource(movieRepository, params.params)
        }.flow.map { pagingData ->
            pagingData.map { movie ->
                MoviePage.MoviePageUIModel(uiMapper.mapToUIModel(movie))
            }
        }

    data class Param(
        val params: PopularMoviesParams
    )
}