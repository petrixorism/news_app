package uz.gita.newsapp.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.newsapp.data.model.common.ArticleData


interface DashboardViewModel {

    val categoriesLiveData: LiveData<List<String>>
    val openArticleLiveData: LiveData<String>
    val oneArticleLiveData: LiveData<ArticleData>



    fun openArticleByCategory(category: String)
    fun getCategories()

}