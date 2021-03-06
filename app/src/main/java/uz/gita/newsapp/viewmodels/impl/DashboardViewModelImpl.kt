package uz.gita.newsapp.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.data.source.local.Categories
import uz.gita.newsapp.domain.repository.NewsRepository
import uz.gita.newsapp.viewmodels.DashboardViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModelImpl @Inject constructor(
    private val repository: NewsRepository,
) :
    ViewModel(), DashboardViewModel {

    override val categoriesLiveData = MutableLiveData(repository.getAllCategories())
    override val openArticleLiveData = MutableLiveData<String>()
    override var oneArticleLiveData: LiveData<ArticleData> = MutableLiveData()


    init {
        oneArticleLiveData = repository.getOneArticle()
    }


    override fun openArticleByCategory(category: String) {
        openArticleLiveData.postValue(category)
    }

    override fun getCategories() {
        categoriesLiveData.value = repository.getAllCategories()
    }
}