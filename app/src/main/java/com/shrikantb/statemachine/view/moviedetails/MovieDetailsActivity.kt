package com.shrikantb.statemachine.view.moviedetails

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.shrikantb.statemachine.databinding.ActivityMovieDetailsBinding
import com.shrikantb.statemachine.domain.statemachine.Action
import com.shrikantb.statemachine.domain.statemachine.state.MovieList
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private val binding: ActivityMovieDetailsBinding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.model = movieDetailsViewModel
        binding.movie = intent?.getParcelableExtra("MOVIE") ?: return

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        movieDetailsViewModel.stateObserver.observe(this, Observer { state ->
            when (state) {
                is MovieList -> finish()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return false
    }

    override fun onBackPressed() {
        movieDetailsViewModel.changeState(Action.BackPressed())
    }
}