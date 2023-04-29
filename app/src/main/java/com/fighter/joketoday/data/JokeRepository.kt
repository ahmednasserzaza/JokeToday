package com.fighter.joketoday.data

import com.fighter.joketoday.data.networking.API

class JokeRepository {
    fun getRandomJoke() = API.jokeService.getRandomJoke()

}