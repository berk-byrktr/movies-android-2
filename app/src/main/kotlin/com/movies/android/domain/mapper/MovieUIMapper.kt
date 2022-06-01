package com.movies.android.domain.mapper

import com.movies.android.common.base.BaseUIMapper
import com.movies.android.data.model.response.MovieResponseModel
import com.movies.android.domain.decider.MovieDecider
import com.movies.android.domain.model.movie.MovieUIModel
import javax.inject.Inject

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class MovieUIMapper @Inject constructor(
    private val movieDecider: MovieDecider,
) : BaseUIMapper<MovieResponseModel, MovieUIModel> {

    override fun mapToUIModel(responseModel: MovieResponseModel?): MovieUIModel =
        responseModel.let {
            MovieUIModel(
                id = it?.id,
                imageUrl = movieDecider.provideImageUrl(responseModel),
                title = it?.title,
                releaseDate = it?.release_date,
                progress = movieDecider.provideProgress(responseModel),
                progressColor = movieDecider.provideProgressColor(responseModel)
            )
        }
}