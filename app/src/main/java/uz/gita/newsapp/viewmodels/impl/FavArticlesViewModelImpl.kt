package uz.gita.newsapp.viewmodels.impl

import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.domain.repository.NewsRepository
import uz.gita.newsapp.viewmodels.FavArticleViewModel
import javax.inject.Inject

@HiltViewModel
class FavArticlesViewModelImpl @Inject constructor(private val repository: NewsRepository) :
    ViewModel(), FavArticleViewModel {

    override val backLiveData: LiveData<Unit> = MutableLiveData()
    override var updateArticleData: LiveData<Unit> = MutableLiveData()
    override var favArticlesLiveData = repository.loadAllFavourites()

    override fun back() {
    }

    override fun loadFavArticles() {
        favArticlesLiveData = MutableLiveData<List<ArticleData>>()
        favArticlesLiveData = repository.loadAllFavourites()
    }

    override fun updateArticle(articleData: ArticleData) {
        updateArticleData = repository.update(articleData)
    }
}