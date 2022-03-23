package com.innavaljokerapp.data

import android.os.Handler
import android.os.Looper
import android.util.Log

class CategoryRemoteDataSource {

    fun findAllCategories(callback: ListCategoryCallback) {

        //INPUT
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )

            Log.i("Teste", response.toString())

            callback.onSuccess(response)
            // onError("FALHA NA CONEXÃO. TENTE NOVAMENTE MAIS TARDE!")

            callback.onComplete()

        }, 4000)

    }
}