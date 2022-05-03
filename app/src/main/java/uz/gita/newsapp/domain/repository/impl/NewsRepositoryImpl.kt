package uz.gita.newsapp.domain.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.data.model.response.BaseResponse
import uz.gita.newsapp.data.model.response.toEntity
import uz.gita.newsapp.data.source.local.Categories
import uz.gita.newsapp.data.source.local.dao.NewsDao
import uz.gita.newsapp.data.source.local.entity.ArticleEntity
import uz.gita.newsapp.data.source.remote.NewsApi
import uz.gita.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    private lateinit var articleData: ArticleData
    private val loadingLiveData = MutableLiveData(true)
    private val updateLiveData = MutableLiveData(Unit)
    private val allFavouritesLiveData = MutableLiveData<List<ArticleData>>()
    private val getOneArticleLiveData = MutableLiveData<ArticleData>()

    override fun loadNewsByCategory(category: String): LiveData<List<ArticleData>> {
        loadingLiveData.value = true

        newsApi.getNewsByCategory(category).enqueue(object : Callback<BaseResponse> {

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {

                loadingLiveData.value = false
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()?.data ?: arrayListOf()
                    newsDao.insertAll(result.map {
                        it.toEntity(category)
                    })
                }

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                loadingLiveData.postValue(false)
            }


        })

        return newsDao.getNewsByCategory(category)

    }

    override fun loadAllFavourites(): LiveData<List<ArticleData>> {

        val categories = Categories.getAllCategory()

        val favList = ArrayList<ArticleData>()

        categories.forEach { category ->
            val articlesByCategory = newsDao.getNewsByCategories(category)
            articlesByCategory.let {
                favList.addAll(articlesByCategory!!.filter { art ->
                    art.isFav
                })
            }
        }
        allFavouritesLiveData.postValue(favList)
        return allFavouritesLiveData

    }

    override fun getAllCategories(): List<String> = Categories.getAllCategory()
    override fun loadingState(): LiveData<Boolean> = loadingLiveData

    override fun update(data: ArticleData): LiveData<Unit> {
        val articleEntity = ArticleEntity(
            data.title,
            data.author,
            data.description,
            data.imageUrl,
            data.readMoreUrl,
            data.time,
            data.url,
            data.category,
            data.isFav
        )
        updateLiveData.postValue(Unit)
        newsDao.updateArticle(articleEntity)
        return updateLiveData

    }

    override fun getOneArticle(): LiveData<ArticleData> {
        loadingLiveData.value = true

        newsApi.getNewsByCategory(Categories.getAllCategory().random())
            .enqueue(object : Callback<BaseResponse> {

                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {

                    loadingLiveData.value = false
                    if (response.isSuccessful && response.body() != null) {
                        val result = response.body()?.data ?: arrayListOf()
                        result.random().let { it ->
                            articleData =
                                ArticleData(
                                    it.title,
                                    it.author.toString(),
                                    it.description.toString(),
                                    it.imageUrl.toString(),
                                    it.readMoreUrl.toString(),
                                    it.time.toString(),
                                    it.url.toString(),
                                    ""
                                )
                        }
                        getOneArticleLiveData.postValue(articleData)
                    }

                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    loadingLiveData.postValue(false)
                }


            })

        return getOneArticleLiveData
    }


}