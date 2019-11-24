package com.shrikantb.statemachine.data.di

import com.shrikantb.statemachine.data.repository.MovieRepository
import com.shrikantb.statemachine.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<MovieRepository> { MovieRepositoryImpl(stateMachine = get()) }
}