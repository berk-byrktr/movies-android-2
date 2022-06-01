package com.movies.android.common.base

import com.movies.android.common.network.ResponseModel
import com.movies.android.common.network.UIModel

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

interface BaseUIMapper<R : ResponseModel, U : UIModel> {
    fun mapToUIModel(responseModel: R?): U?
}

