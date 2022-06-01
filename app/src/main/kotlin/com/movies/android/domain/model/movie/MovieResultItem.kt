package com.movies.android.domain.model.movie

/**
 * Created by Berk Bayraktar on 28.01.2022.
 */

data class MovieResultItem(
    var result: MoviePage?,
    var itemAction: ((MovieUIModel) -> (Unit))?
)