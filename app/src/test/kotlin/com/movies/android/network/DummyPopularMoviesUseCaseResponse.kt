package com.movies.android.network

import com.movies.android.common.base.BaseResponse
import com.movies.android.common.network.Resource
import com.movies.android.data.model.request.PopularMoviesBody
import com.movies.android.data.model.request.PopularMoviesParams
import com.movies.android.data.model.response.MovieResponseModel
import com.movies.android.domain.usecase.PopularMoviesUseCase

/**
 * Created by Berk Bayraktar on 28.04.2022
 */

object DummyPopularMoviesUseCaseResponse : BaseDummyData() {

    val createPopularMoviesBody = PopularMoviesBody(
        language = "en-US",
        page = 1,
    )

    val createPopularMoviesParam =
        PopularMoviesUseCase.Param(PopularMoviesParams(language = "en-US"))

    val createPopularMoviesSuccessResponse = Resource.Success(
        BaseResponse(
            results = listOf(
                MovieResponseModel(
                    adult = false,
                    backdrop_path = "/An1nKjXahrChfEbZ3MAE8fsiut1.jpg",
                    id = "661791",
                    original_language = "es",
                    original_title = "La abuela",
                    overview = "A Paris model must return to Madrid where her grandmother, who raised her, has had a stroke. But spending just a few days with this relative turns into an unexpected nightmare.",
                    popularity = "1141.227",
                    poster_path = "/eIUixNvox4U4foL5Z9KbN9HXYSM.jpg",
                    release_date = "2022-01-28",
                    title = "The Grandmother",
                    video = false,
                    vote_average = 6f,
                    vote_count = 134,
                    genres_ids = listOf(1, 2, 3)
                )
            ),
            page = 1,
            total_pages = 33360,
            total_results = 667200,
        )
    )

    val createPopularMoviesFailureResponse = Resource.Failure

    val failureResult = Resource.Failure

}