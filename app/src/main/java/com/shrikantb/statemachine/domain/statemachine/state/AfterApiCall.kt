package com.shrikantb.statemachine.domain.statemachine.state

import com.shrikantb.statemachine.domain.model.Movie
import com.shrikantb.statemachine.domain.statemachine.Action

class AfterApiCall(val movies: ArrayList<Movie>) : State {
    override fun consumeAction(action: Action) = this
}