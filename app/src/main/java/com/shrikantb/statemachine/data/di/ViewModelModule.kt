package com.shrikantb.statemachine.data.di

import com.shrikantb.statemachine.view.moviedetails.MovieDetailsViewModel
import com.shrikantb.statemachine.view.movies.MoviesViewModel
import com.shrikantb.statemachine.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MoviesViewModel(movieRepository = get()) }
    viewModel { MovieDetailsViewModel() }
}