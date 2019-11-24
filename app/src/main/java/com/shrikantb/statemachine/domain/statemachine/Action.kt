package com.shrikantb.statemachine.domain.statemachine

import com.shrikantb.statemachine.domain.model.Movie

sealed class Action {
    class ScreenNavigation : Action()
    class StartLoading : Action()
    class StopLoading(val movies: ArrayList<Movie>) : Action()
    class MovieClicked(val movie: Movie) : Action()
    class BackPressed : Action()
}