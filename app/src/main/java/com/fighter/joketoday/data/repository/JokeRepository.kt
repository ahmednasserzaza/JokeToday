package com.fighter.joketoday.data.repository

import com.fighter.joketoday.data.networking.API

class JokeRepository {
    fun getRandomJoke() = API.jokeService.getRandomJoke()

}