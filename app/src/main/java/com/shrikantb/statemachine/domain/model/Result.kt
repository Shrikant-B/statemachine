package com.shrikantb.statemachine.domain.model

sealed class Result<out T : Any> {
    data class SuccessState<out T : Any>(val data: T) : Result<T>()
    data class FailureState(val exception: Exception) : Result<Nothing>()
}