package com.shrikantb.statemachine.data.remote

import com.shrikantb.statemachine.domain.model.MovieResponse
import retrofit2.Retrofit

class StateMachineApiImpl(val retrofit: Retrofit) : StateMachineApi {
    val stateMachine = retrofit.create(StateMachineApi::class.java)

    override suspend fun upcomingMovies(): MovieResponse {
        return stateMachine.upcomingMovies()
    }
}