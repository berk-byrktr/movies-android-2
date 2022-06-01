package com.movies.android.feature.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.movies.android.common.Constants
import com.movies.android.common.base.BaseViewModel
import com.movies.android.data.model.request.PopularMoviesParams
import com.movies.android.domain.decider.MovieDecider
import com.movies.android.domain.model.movie.MoviePage
import com.movies.android.domain.model.movie.MovieResultItem
import com.movies.android.domain.usecase.PopularMoviesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class MoviesViewModel @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val movieDecider: MovieDecider
) : BaseViewModel() {

    private val _moviesEventFlow = MutableStateFlow<MoviesEvent>(MoviesEvent.Idle)
    val moviesEventFlow: StateFlow<MoviesEvent> = _moviesEventFlow

    private val _clickedFlow = MutableStateFlow<MoviesClickEvent>(MoviesClickEvent.Idle)
    val clickedFlow: StateFlow<MoviesClickEvent> = _clickedFlow

    private val _moviesResult = MutableLiveData<PagingData<MovieResultItem>>()
    val moviesResult: LiveData<PagingData<MovieResultItem>> get() = _moviesResult

    private val _loadingFlow = MutableStateFlow<MoviesLoadingEvent>(MoviesLoadingEvent.Idle)
    val loadingFlow: StateFlow<MoviesLoadingEvent> = _loadingFlow

    private var moviesJob: Job? = null

    fun getPopularMovies() {
        moviesJob?.cancel()
        moviesJob = viewModelScope.launch {
            popularMovies().collectLatest {
                _moviesResult.value = it.map { item ->
                    MovieResultItem(
                        result = item,
                        itemAction = {
                            _clickedFlow.value =
                                MoviesClickEvent.DataClicked(MoviePage.getItem(item))
                            _clickedFlow.value = MoviesClickEvent.Idle
                        }
                    )
                }
            }
        }
    }

    private suspend fun popularMovies(): Flow<PagingData<MoviePage>> {
        return popularMoviesUseCase(
            PopularMoviesUseCase.Param(
                PopularMoviesParams(
                    language = Constants.LANGUAGE_ENGLISH
                )
            )
        ).cachedIn(viewModelScope)
    }

    fun setMoviesEvent(event: MoviesEvent) {
        viewModelScope.launch {
            _moviesEventFlow.value = event
        }
    }

    fun setLoadingEvent(loading: Boolean = false) {
        viewModelScope.launch {
            _loadingFlow.value =
                if (loading) MoviesLoadingEvent.Loading else MoviesLoadingEvent.Idle
        }
    }
}
