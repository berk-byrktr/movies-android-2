package com.movies.android.ui.customview.page

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class ViewStatePage(
    private val status: Status,
    val error: Throwable? = null,
) {

    enum class Status {
        SUCCESS, LOADING, ERROR, CONTENT
    }

    fun isSuccess() = status == Status.SUCCESS

    fun isLoading() = status == Status.LOADING

    fun isError() = status == Status.ERROR

    fun getState() =
        when (status) {
            Status.LOADING -> CustomPage.State.LOADING
            Status.SUCCESS -> CustomPage.State.CONTENT
            Status.ERROR -> CustomPage.State.ERROR
            Status.CONTENT -> CustomPage.State.CONTENT
        }
}