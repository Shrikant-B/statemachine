package com.shrikantb.statemachine.view.movies

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrikantb.statemachine.data.repository.MovieRepository
import com.shrikantb.statemachine.domain.model.Movie
import com.shrikantb.statemachine.domain.model.Result
import com.shrikantb.statemachine.domain.statemachine.Action
import com.shrikantb.statemachine.domain.statemachine.state.MovieList
import com.shrikantb.statemachine.domain.statemachine.state.State
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MoviesViewModel(val movieRepository: MovieRepository) : ViewModel() {
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
            when (val response = movieRepository.getMovies()) {
                is Result.SuccessState -> {
                    if (response.data.movieList.isNotEmpty()) {
                        moviesList.addAll(response.data.movieList)
                        stateObserver.postValue(state.consumeAction(Action.StopLoading(moviesList)))
                    } else moviesList.addAll(emptyList())
                }

                is Result.FailureState -> {
                    response.exception.printStackTrace()
                    stateObserver.postValue(state.consumeAction(Action.StopLoading(ArrayList())))
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}