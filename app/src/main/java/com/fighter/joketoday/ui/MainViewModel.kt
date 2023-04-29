package com.fighter.joketoday.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fighter.joketoday.data.JokeRepository
import com.fighter.joketoday.data.model.JokeResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"
    private val compositeDisposable = CompositeDisposable()
    private val repository = JokeRepository()
    private val _randomJoke = MutableLiveData<JokeResponse>()
    val randomJoke: LiveData<JokeResponse>
        get() = _randomJoke

    init {
        getRandomJoke()
    }

    fun getRandomJoke() {
        val randomJoke = repository.getRandomJoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onGetRandomJoke, this::onError)
        compositeDisposable.add(randomJoke)
    }

    private fun onGetRandomJoke(jokeResponse: JokeResponse) {
        _randomJoke.postValue(jokeResponse)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "${e.message}")
    }
}