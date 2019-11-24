package com.shrikantb.statemachine.domain.statemachine.state

import com.shrikantb.statemachine.domain.statemachine.Action

class MovieList : State {
    override fun consumeAction(action: Action): State {
        return when (action) {
            is Action.StartLoading -> BeforeApiCall()
            is Action.StopLoading -> AfterApiCall(action.movies)
            is Action.MovieClicked -> MovieDetails(action.movie)
            is Action.BackPressed -> TerminateCurrent()
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}