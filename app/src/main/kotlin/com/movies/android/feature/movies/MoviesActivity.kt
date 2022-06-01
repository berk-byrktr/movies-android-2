package com.movies.android.feature.movies

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.android.App
import com.movies.android.common.base.BaseActivity
import com.movies.android.common.base.BaseViewModel
import com.movies.android.databinding.ActivityMoviesBinding
import com.movies.android.di.component.DaggerMoviesComponent
import com.movies.android.ui.adapter.MoviesAdapter
import com.movies.android.ui.customview.page.ViewStatePage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class MoviesActivity : BaseActivity() {

    private val vm: MoviesViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityMoviesBinding
    private val moviesAdapter = MoviesAdapter()

    override val viewModel: BaseViewModel get() = vm

    override fun bindView() {
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initDI() {
        DaggerMoviesComponent.builder()
            .coreComponent(App.instance.getCoreComponent())
            .build()
            .inject(this)
    }

    override fun onCreate() {
        initUI()
        initObservers()
        vm.setMoviesEvent(MoviesEvent.GetPopularMovies)
    }

    private fun initUI() {
        with(binding) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(this@MoviesActivity)
                adapter = moviesAdapter
            }
            swipeRefreshLayout.setOnRefreshListener {
                moviesAdapter.refresh()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            vm.moviesEventFlow.collect {
                when (it) {
                    MoviesEvent.GetPopularMovies -> vm.getPopularMovies()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            vm.loadingFlow.collect {
                when (it) {
                    is MoviesLoadingEvent.Loading -> startLoading()
                    is MoviesLoadingEvent.Idle -> endLoading()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            moviesAdapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> vm.setLoadingEvent(true)
                    is LoadState.NotLoading -> vm.setLoadingEvent(false)
                    is LoadState.Error -> vm.setLoadingEvent(false)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            vm.clickedFlow.collect {
                when (it) {
                    is MoviesClickEvent.DataClicked -> {
                        //DO NOTHING
                    }
                }
            }
        }
        vm.moviesResult.observe(this) {
            lifecycleScope.launchWhenCreated {
                moviesAdapter.submitData(it)
            }
        }
    }

    private fun startLoading() =
        with(binding) { viewStatePage = ViewStatePage(ViewStatePage.Status.LOADING) }

    private fun endLoading() =
        with(binding) { viewStatePage = ViewStatePage(ViewStatePage.Status.CONTENT) }


    companion object {
        fun createStartActivityIntent(context: Context): Intent =
            Intent(context, MoviesActivity::class.java)
    }
}