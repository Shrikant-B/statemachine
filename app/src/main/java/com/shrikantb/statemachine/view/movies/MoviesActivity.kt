package com.shrikantb.statemachine.view.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrikantb.statemachine.databinding.ActivityMoviesBinding
import com.shrikantb.statemachine.domain.statemachine.Action
import com.shrikantb.statemachine.domain.statemachine.state.AfterApiCall
import com.shrikantb.statemachine.domain.statemachine.state.BeforeApiCall
import com.shrikantb.statemachine.domain.statemachine.state.MovieDetails
import com.shrikantb.statemachine.domain.statemachine.state.TerminateCurrent
import com.shrikantb.statemachine.view.moviedetails.MovieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {
    private val moviesViewModel: MoviesViewModel by viewModel()
    private val adapter: MoviesRecyclerAdapter by lazy { MoviesRecyclerAdapter(moviesViewModel) }
    private val binding: ActivityMoviesBinding by lazy {
        ActivityMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvMovies.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.rvMovies.adapter = adapter

        moviesViewModel.stateObserver.observe(this, Observer { state ->
            when (state) {
                is BeforeApiCall -> {
                    binding.moviesLoader.visibility = View.VISIBLE
                    binding.rvMovies.visibility = View.GONE
                }
                is AfterApiCall -> {
                    binding.moviesLoader.visibility = View.GONE
                    binding.rvMovies.visibility = View.VISIBLE
                    adapter.setMovies(state.movies)
                    adapter.notifyDataSetChanged()
                }
                is MovieDetails -> {
                    startActivity(Intent(this, MovieDetailsActivity::class.java).apply {
                        putExtra("MOVIE", state.movie)
                    })
                }
                is TerminateCurrent -> finish()
            }
        })
        moviesViewModel.getMovies()
    }

    override fun onBackPressed() {
        moviesViewModel.changeState(Action.BackPressed())
    }
}