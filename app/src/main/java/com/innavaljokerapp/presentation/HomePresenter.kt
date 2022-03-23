package com.innavaljokerapp.presentation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.innavaljokerapp.data.CategoryRemoteDataSource
import com.innavaljokerapp.data.ListCategoryCallback
import com.innavaljokerapp.model.Category
import com.innavaljokerapp.view.CategoryItem
import com.innavaljokerapp.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {

        val start = 320// H-matiz
        val end = 359 // H-matiz
        val diff = (end-start)  / response.size

        val categories = response.mapIndexed { index, s ->
            val hsv = floatArrayOf(

                start + (diff * index).toFloat(),
                100.0f,
                100.0f
            )

            Category(s, Color.HSVToColor(hsv).toLong())
        }
        view.showCategories(categories)
    }

    override fun onComplete() {
        view.hideProgress()
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }


}