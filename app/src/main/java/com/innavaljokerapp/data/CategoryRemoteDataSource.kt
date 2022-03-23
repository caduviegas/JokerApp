package com.innavaljokerapp.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CategoryRemoteDataSource {

    fun findAllCategories(callback: ListCategoryCallback) {

        HTTPClient.retrofit() // Aqui tem a instancia do retrofit pronta
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories()
            .enqueue(object : Callback<List<String>> {

                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val categories = response.body()
                        callback.onSuccess(categories ?: emptyList())
                    } else {
                        // Quando o servidor devolve status de erro < 500
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro Desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError(t.message ?: "Erro Interno")
                    callback.onComplete()
                }

            })

    }
}