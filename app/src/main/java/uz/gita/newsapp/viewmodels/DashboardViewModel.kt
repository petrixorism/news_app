package uz.gita.newsapp.viewmodels

import androidx.lifecycle.LiveData


interface DashboardViewModel {

    val categoriesLiveData: LiveData<List<String>>
    val openArticleLiveData: LiveData<String>



    fun openArticleByCategory(category: String)
    fun getCategories()
}