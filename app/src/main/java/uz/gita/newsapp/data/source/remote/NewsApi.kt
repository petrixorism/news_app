package uz.gita.newsapp.data.source.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsapp.data.model.response.BaseResponse


interface NewsApi {

    @GET("news")
    fun getNewsByCategory(@Query("category") category: String): Call<BaseResponse>

}