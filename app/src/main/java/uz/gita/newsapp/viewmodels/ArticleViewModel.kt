package uz.gita.newsapp.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.data.source.local.entity.ArticleEntity

interface ArticleViewModel {

    val loadingLiveData: LiveData<Boolean>
    val backLiveData: LiveData<Unit>
    val updateArticleData: LiveData<Unit>
    val articlesLiveData: LiveData<List<ArticleData>>

    fun back()

    fun loadArticlesByCategory(category: String)
    fun updateArticle(articleData: ArticleData)

}