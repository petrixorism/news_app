package uz.gita.newsapp.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.newsapp.data.model.common.ArticleData

interface FavArticleViewModel {


    val backLiveData: LiveData<Unit>
    val updateArticleData: LiveData<Unit>
    val favArticlesLiveData: LiveData<List<ArticleData>>

    fun back()

    fun loadFavArticles()
    fun updateArticle(articleData: ArticleData)

}