package com.movies.android.usecase

import com.google.common.truth.Truth.assertThat
import com.movies.android.data.repository.movie.MovieRepository
import com.movies.android.domain.decider.MovieDecider
import com.movies.android.domain.mapper.MovieUIMapper
import com.movies.android.domain.usecase.PopularMoviesUseCase
import com.movies.android.network.DummyPopularMoviesUseCaseResponse
import com.movies.android.util.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Berk Bayraktar on 28.04.2022
 */

@ExperimentalCoroutinesApi
class PopularMoviesUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mainTestCoroutineRule = MainCoroutineRule()

    private lateinit var popularMoviesUseCase: PopularMoviesUseCase
    private var movieRepository = mockk<MovieRepository>()

    @Before
    fun setUp() {
        popularMoviesUseCase = PopularMoviesUseCase(
            movieRepository,
            MovieUIMapper(MovieDecider()),
            testDispatcher
        )
    }

    @Test
    fun `check that when call getExecutable function, it returns movies, if it success`() =
        mainTestCoroutineRule.runBlockingTest {
            coEvery { movieRepository.getPopularMovies(DummyPopularMoviesUseCaseResponse.createPopularMoviesBody) } returns DummyPopularMoviesUseCaseResponse.createPopularMoviesSuccessResponse
            val result =
                popularMoviesUseCase(DummyPopularMoviesUseCaseResponse.createPopularMoviesParam)
            assertThat(result).isEqualTo(DummyPopularMoviesUseCaseResponse.createPopularMoviesSuccessResponse)
        }

    @Test
    fun `check that if getExecutable function takes error response, it returns Failure Response`() =
        mainTestCoroutineRule.runBlockingTest {
            coEvery { movieRepository.getPopularMovies(DummyPopularMoviesUseCaseResponse.createPopularMoviesBody) } returns DummyPopularMoviesUseCaseResponse.createPopularMoviesFailureResponse
            val result =
                popularMoviesUseCase(DummyPopularMoviesUseCaseResponse.createPopularMoviesParam)
            assertThat(result).isEqualTo(DummyPopularMoviesUseCaseResponse.failureResult)
        }
}
