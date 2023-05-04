package com.fighter.joketoday.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fighter.joketoday.data.model.JokeResponse
import com.fighter.joketoday.data.repository.JokeRepository
import com.fighter.joketoday.utils.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

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
        val randomJokeDisposable = repository.getRandomJoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetRandomJoke, ::onError)

        addToCompositeDisposable(randomJokeDisposable)
    }

    private fun onGetRandomJoke(jokeResponse: State<JokeResponse?>) {
        _randomJoke.postValue(jokeResponse)
    }

    private fun onError(e: Throwable) {
        log(e.message.toString())
    }

}