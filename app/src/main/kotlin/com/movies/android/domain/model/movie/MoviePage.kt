package com.movies.android.domain.model.movie

/**
 * Created by Berk Bayraktar on 28.01.2022.
 */

sealed class MoviePage {
    data class MoviePageUIModel(val item: MovieUIModel) : MoviePage()

    companion object {
        fun getItem(moviePage: MoviePage): MovieUIModel {
            return when (moviePage) {
                is MoviePageUIModel -> moviePage.item
            }
        }
    }
}