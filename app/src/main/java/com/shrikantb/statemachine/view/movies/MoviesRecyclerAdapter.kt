package com.shrikantb.statemachine.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shrikantb.statemachine.databinding.RecyclerAdapterMoviesBinding
import com.shrikantb.statemachine.domain.model.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MoviesRecyclerAdapter(val moviesViewModel: MoviesViewModel) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder>() {
    private val movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerAdapterMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.onBind(movie, position)
    }

    fun setMovies(list: ArrayList<Movie>) {
        if (list.isNotEmpty()) {
            movies.clear()
            movies.addAll(list)
        }
    }

    inner class ViewHolder(val binding: RecyclerAdapterMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: Movie, position: Int) {
            binding.item = movie
            binding.position = position
            binding.model = moviesViewModel
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl", "app:actionHandler")
        fun showMoviePoster(v: AppCompatImageView, url: String, viewModel: MoviesViewModel) {
            viewModel.imageLoader.set(View.VISIBLE)
            Picasso.get().load(url).fit().into(v, object : Callback {
                override fun onSuccess() {
                    viewModel.imageLoader.set(View.GONE)
                }

                override fun onError(e: Exception?) {
                    viewModel.imageLoader.set(View.GONE)
                }
            })
        }
    }
}