package com.shrikantb.statemachine.view.movies

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrikantb.statemachine.data.repository.MovieRepository
import com.shrikantb.statemachine.domain.model.Movie
import com.shrikantb.statemachine.domain.statemachine.Action
import com.shrikantb.statemachine.domain.statemachine.state.MovieList
import com.shrikantb.statemachine.domain.statemachine.state.State
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MoviesViewModel(val movieRepository: MovieRepository) : ViewModel() {
    val loading = ObservableBoolean(false)
    val imageLoader = ObservableInt(View.GONE)
    val moviesList = ArrayList<Movie>()
    private var state: State = MovieList()
    val stateObserver: MutableLiveData<State> = MutableLiveData()

    fun changeState(action: Action) {
        stateObserver.value = state.consumeAction(action)
    }

    fun onMovieClicked(movie: Movie) {
        stateObserver.value = state.consumeAction(Action.MovieClicked(movie))
    }

    fun getMovies() {
        viewModelScope.launch {
            stateObserver.postValue(state.consumeAction(Action.StartLoading()))
            val movieResponse = movieRepository.getMovies()
            movieResponse?.let {
                if (movieResponse.movieList.isNotEmpty()) {
                    moviesList.addAll(movieResponse.movieList)
                } else {
                    moviesList.addAll(emptyList())
                }
            }
            stateObserver.postValue(state.consumeAction(Action.StopLoading(moviesList)))
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}