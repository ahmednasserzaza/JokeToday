package com.fighter.joketoday.data.networking

import com.fighter.joketoday.data.model.JokeResponse
import com.fighter.joketoday.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface JokeService {
    @GET("joke/Any")
    suspend fun getRandomJoke(): Response<JokeResponse>
}