package com.shrikantb.statemachine.data.repository

import com.shrikantb.statemachine.data.remote.StateMachineApi
import com.shrikantb.statemachine.domain.model.MovieResponse
import com.shrikantb.statemachine.domain.model.Result

class MovieRepositoryImpl(val stateMachine: StateMachineApi) : MovieRepository {

    override suspend fun getMovies(): Result<MovieResponse> {
        val response = stateMachine.upcomingMovies()
        return if (response.isSuccessful) {
            Result.SuccessState(response.body()!!)
        } else {
            Result.FailureState(RuntimeException())
        }
    }
}