package com.movies.android.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.movies.android.common.extension.viewBinding
import com.movies.android.databinding.RowMovieItemBinding
import com.movies.android.domain.model.movie.MoviePage
import com.movies.android.domain.model.movie.MovieResultItem

/**
 * Created by Berk Bayraktar on 27.04.2022
 */

class MoviesAdapter : PagingDataAdapter<MovieResultItem, MoviesAdapter.BaseViewHolder>(
    MOVIE_COMPANION
) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieItemViewHolder(parent.viewBinding(RowMovieItemBinding::inflate))
    }

    class MovieItemViewHolder(private val binding: RowMovieItemBinding) :
        BaseViewHolder(binding) {
        override fun bind(item: MovieResultItem?) {
            item?.let {
                it.result?.let { result ->
                    binding.movie = MoviePage.getItem(result)
                    binding.executePendingBindings()
                    binding.root.setOnClickListener {
                        item.itemAction?.invoke(MoviePage.getItem(result))
                    }
                }
            }
        }
    }

    abstract class BaseViewHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: MovieResultItem?)
    }

    companion object {
        private val MOVIE_COMPANION =
            object : DiffUtil.ItemCallback<MovieResultItem>() {
                override fun areItemsTheSame(
                    oldItem: MovieResultItem,
                    newItem: MovieResultItem
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: MovieResultItem,
                    newItem: MovieResultItem
                ): Boolean {
                    val oldItemResult = oldItem.result?.let {
                        MoviePage.getItem(it)
                    }
                    val newItemResult = newItem.result?.let {
                        MoviePage.getItem(it)
                    }
                    return oldItemResult?.id == newItemResult?.id
                }
            }
    }
}