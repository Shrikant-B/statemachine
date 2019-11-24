package com.shrikantb.statemachine.data.repository

import com.shrikantb.statemachine.domain.model.MovieResponse

interface MovieRepository {
    suspend fun getMovies(): MovieResponse?
}