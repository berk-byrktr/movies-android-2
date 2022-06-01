package com.movies.android

import com.google.common.truth.Truth
import com.movies.android.ui.customview.page.ViewStatePage
import org.junit.Test

/**
 * Created by Berk Bayraktar on 28.04.2022
 */

class ViewStatePageTest {

    @Test
    fun `should return loading true when status is loading`() {

        // Given
        val givenViewState = ViewStatePage(status = ViewStatePage.Status.LOADING)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is error`() {

        // Given
        val givenViewState = ViewStatePage(status = ViewStatePage.Status.ERROR)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is success`() {

        // Given
        val givenViewState = ViewStatePage(status = ViewStatePage.Status.SUCCESS)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }
}