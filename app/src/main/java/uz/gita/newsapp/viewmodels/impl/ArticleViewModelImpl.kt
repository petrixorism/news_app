package uz.gita.newsapp.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.domain.repository.NewsRepository
import uz.gita.newsapp.viewmodels.ArticleViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModelImpl @Inject constructor(private val repository: NewsRepository) :
    ArticleViewModel, ViewModel() {

    override val loadingLiveData: LiveData<Boolean> = repository.loadingState()
    override val backLiveData = MutableLiveData<Unit>()
    override var updateArticleData:LiveData<Unit> = MutableLiveData()
    override var articlesLiveData: LiveData<List<ArticleData>> = MutableLiveData()

    override fun back() {
        backLiveData.value = Unit
    }

    override fun loadArticlesByCategory(category: String) {
        articlesLiveData = repository.loadNewsByCategory(category)
    }

    override fun updateArticle(articleData: ArticleData) {
        updateArticleData = repository.update(articleData)
    }
}