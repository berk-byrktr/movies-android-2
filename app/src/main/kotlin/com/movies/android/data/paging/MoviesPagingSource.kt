package com.movies.android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movies.android.common.Constants
import com.movies.android.common.network.Resource
import com.movies.android.data.model.request.PopularMoviesBody
import com.movies.android.data.model.request.PopularMoviesParams
import com.movies.android.data.model.response.MovieResponseModel
import com.movies.android.data.repository.movie.MovieRepository
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Berk Bayraktar on 27.04.2022
 */

class MoviesPagingSource(
    private val movieRepository: MovieRepository,
    private val moviesParams: PopularMoviesParams
) : PagingSource<Int, MovieResponseModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseModel> {
        return try {
            val currentPageNumber = params.key ?: Constants.Pagination.INITIAL_PAGE_NUMBER
            val result = movieRepository.getPopularMovies(
                PopularMoviesBody(
                    language = moviesParams.language,
                    page = currentPageNumber,
                )
            )

            var page: Int? = null
            var nextPageNumber: Int? = null
            var list: List<MovieResponseModel>? = null

            when (result) {
                is Resource.Success -> {
                    page = result.data.page
                    list = result.data.results
                }
                is Resource.Failure -> run { return LoadResult.Error(Throwable()) }
            }

            if (!list.isNullOrEmpty() && page != null) {
                nextPageNumber = page + Constants.Pagination.PAGE_INCREMENT
            }

            LoadResult.Page(
                data = list ?: emptyList(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponseModel>): Int? = null
}