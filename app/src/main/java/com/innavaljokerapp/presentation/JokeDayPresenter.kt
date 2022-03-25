package com.innavaljokerapp.presentation

import com.innavaljokerapp.view.JokeDayFragment
import com.innavaljokerapp.data.JokeCallback
import com.innavaljokerapp.data.JokeDayRemoteDataSource
import com.innavaljokerapp.model.Joke

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
) : JokeCallback {

    fun findRandom() {
        view.showProgress()
        dataSource.findRandom(this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}