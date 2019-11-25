package com.shrikantb.statemachine.view.moviedetails

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrikantb.statemachine.domain.statemachine.Action
import com.shrikantb.statemachine.domain.statemachine.state.State
import com.shrikantb.statemachine.domain.statemachine.state.MovieDetails
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.cancel

class MovieDetailsViewModel : ViewModel() {
    val imageLoader = ObservableInt(View.GONE)
    private var state: State = MovieDetails()
    val stateObserver: MutableLiveData<State> = MutableLiveData()

    fun changeState(action: Action) {
        stateObserver.value = state.consumeAction(action)
    }

    override fun onCleared() {
        super.onCleared()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl", "app:actionHandler")
        fun showMoviePoster(v: AppCompatImageView, url: String, viewModel: MovieDetailsViewModel) {
            viewModel.imageLoader.set(View.VISIBLE)
            Picasso.get().load(url).into(v, object : Callback {
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