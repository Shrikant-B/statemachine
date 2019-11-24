package com.shrikantb.statemachine.data.repository

import com.shrikantb.statemachine.data.remote.StateMachineApi
import com.shrikantb.statemachine.domain.model.MovieResponse

class MovieRepositoryImpl(val stateMachine: StateMachineApi) : MovieRepository {

    override suspend fun getMovies(): MovieResponse {
        return stateMachine.upcomingMovies()
    }
}