package com.fighter.joketoday.data.repository

import com.fighter.joketoday.data.model.JokeResponse
import com.fighter.joketoday.data.networking.API
import com.fighter.joketoday.utils.State
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

class JokeRepository {
    fun getRandomJoke(): Observable<State<JokeResponse?>> {
        return wrapWithSingle(API.jokeService::getRandomJoke)
    }

    private fun <T> wrapWithSingle(function: () -> Observable<Response<T>>): Observable<State<T?>> {
        return function.invoke()
            .map {
                if (it.isSuccessful) {
                    State.Success(it.body())
                } else {
                    State.Error(it.message())
                }
            }
            .onErrorReturn { State.Error(it.message.toString()) }
            .startWithItem(State.Loading)
    }
}
