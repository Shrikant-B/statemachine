package com.shrikantb.statemachine.data.remote

import com.shrikantb.statemachine.domain.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface StateMachineApi {
    @GET("movie/upcoming")
    suspend fun upcomingMovies(): Response<MovieResponse>
}