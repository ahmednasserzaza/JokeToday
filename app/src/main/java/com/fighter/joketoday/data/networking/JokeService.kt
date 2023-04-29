package com.fighter.joketoday.data.networking

import com.fighter.joketoday.data.model.JokeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface JokeService {
    @GET("joke/Any")
    fun getRandomJoke(): Single<JokeResponse>
}