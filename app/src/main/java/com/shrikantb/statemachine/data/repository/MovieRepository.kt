package com.shrikantb.statemachine.data.repository

import com.shrikantb.statemachine.domain.model.MovieResponse
import com.shrikantb.statemachine.domain.model.Result

interface MovieRepository {
    suspend fun getMovies(): Result<MovieResponse>
}