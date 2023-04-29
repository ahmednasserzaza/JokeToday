package com.fighter.joketoday.utils

sealed class State<out T> {

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val errorMessage: String) : State<Nothing>()

    object Loading : State<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}
