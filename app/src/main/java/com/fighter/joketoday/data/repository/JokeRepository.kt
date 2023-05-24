package com.fighter.joketoday.data.repository

import com.fighter.joketoday.data.model.JokeResponse
import com.fighter.joketoday.data.networking.API
import com.fighter.joketoday.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class JokeRepository {
    fun getRandomJoke(): Flow<State<JokeResponse?>> {
        return wrapWithFlow(API.jokeService::getRandomJoke)
    }

    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<State<T?>> {
        return flow {
            emit(State.Loading)
            try {
                val result = function()
                if (result.isSuccessful) {
                    emit(State.Success(result.body()))
                } else {
                    emit(State.Error(result.message()))
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }

        }

    }
}
