package com.innavaljokerapp.presentation

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

        val categories = response.map {
            Category(it, 0xFFFF0000)
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