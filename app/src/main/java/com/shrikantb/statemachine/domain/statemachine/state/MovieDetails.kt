package com.shrikantb.statemachine.domain.statemachine.state

import com.shrikantb.statemachine.domain.model.Movie
import com.shrikantb.statemachine.domain.statemachine.Action

class MovieDetails(val movie: Movie? = null) :
    State {
    override fun consumeAction(action: Action): State {
        return when (action) {
            is Action.BackPressed -> MovieList()
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}