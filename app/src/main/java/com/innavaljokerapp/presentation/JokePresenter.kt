package com.innavaljokerapp.presentation

import com.innavaljokerapp.data.JokeCallback
import com.innavaljokerapp.data.JokeRemoteDataSource
import com.innavaljokerapp.model.Joke
import com.innavaljokerapp.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
) : JokeCallback {

    fun findBy(categoryName: String) {
        view.showProgress()
        dataSource.findBy(categoryName, this)
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