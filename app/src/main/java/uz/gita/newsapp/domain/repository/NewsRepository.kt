package uz.gita.newsapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.data.source.local.entity.ArticleEntity


interface NewsRepository {

    fun loadNewsByCategory(category: String): LiveData<List<ArticleData>>

    fun loadAllFavourites(): LiveData<List<ArticleData>>

    fun getAllCategories(): List<String>

    fun loadingState(): LiveData<Boolean>

    fun update(data: ArticleData):LiveData<Unit>

    fun getOneArticle(): LiveData<ArticleData>

}