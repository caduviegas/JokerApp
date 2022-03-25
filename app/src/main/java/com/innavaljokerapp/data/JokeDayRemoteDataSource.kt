package com.innavaljokerapp.data

import com.innavaljokerapp.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeDayRemoteDataSource {

    fun findRandom( callback: JokeCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findRandom()
            .enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        callback.onSuccess(joke ?: throw RuntimeException("Piada Não Encontrada"))
                    } else {
                        // Quando o servidor devolve status de erro < 500
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro Desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Erro Interno")
                    callback.onComplete()
                }

            })
    }
}