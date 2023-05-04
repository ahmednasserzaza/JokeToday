package com.fighter.joketoday.data.repository

import com.fighter.joketoday.data.model.JokeResponse
import com.fighter.joketoday.data.networking.API
import com.fighter.joketoday.utils.State
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

class JokeRepository {
    fun getRandomJoke(): Observable<State<JokeResponse?>> {
        return wrapWithObservable(API.jokeService.getRandomJoke())
    }

    private fun <T> wrapWithObservable(response: Observable<Response<T>>): Observable<State<T?>> {
        return response
            .map {
                if (it.isSuccessful) {
                    State.Success(it.body())
                } else {
                    State.Error(it.message())
                }
            }
            .startWithItem(State.Loading)
    }
}
