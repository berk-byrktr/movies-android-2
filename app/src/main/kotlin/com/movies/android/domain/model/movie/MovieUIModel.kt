package com.movies.android.domain.model.movie

import com.movies.android.common.network.UIModel

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class MovieUIModel(
    val id: String?,
    val imageUrl: String?,
    val title: String?,
    val releaseDate: String?,
    val progress: Int?,
    val progressColor: Int?
) : UIModel