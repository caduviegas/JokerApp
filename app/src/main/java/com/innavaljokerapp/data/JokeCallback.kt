package com.innavaljokerapp.data

import com.innavaljokerapp.model.Joke

interface JokeCallback {

    fun onSuccess ( response: Joke)

    fun onError (response:String)

    fun onComplete()
}