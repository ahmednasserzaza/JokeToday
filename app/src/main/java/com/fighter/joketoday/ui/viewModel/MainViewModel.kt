package com.fighter.joketoday.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fighter.joketoday.data.model.JokeResponse
import com.fighter.joketoday.data.repository.JokeRepository
import com.fighter.joketoday.utils.State
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    override val tag: String = this::class.java.simpleName
    private val repository = JokeRepository()

    private val _randomJoke = MutableLiveData<State<JokeResponse?>>()
    val randomJoke: LiveData<State<JokeResponse?>>
        get() = _randomJoke

    init {
        getJoke()
    }

    fun getJoke() {
        viewModelScope.launch {
            repository.getRandomJoke().collect {
                _randomJoke.postValue(it)
            }
        }

    }
}