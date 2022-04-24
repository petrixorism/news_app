package uz.gita.newsapp.data.model.response

import com.google.gson.annotations.SerializedName
import uz.gita.newsapp.data.source.local.entity.ArticleEntity

data class ArticleResponse(

    val title: String,

    val author: String?,

    @SerializedName("content")
    val description: String?,

    @SerializedName("imageUrl")
    val imageUrl: String?,

    @SerializedName("readMoreUrl")
    val readMoreUrl: String?,

    @SerializedName("date")
    val time: String?,

    val url: String?

)

fun ArticleResponse.toEntity(category: String) = ArticleEntity(title, author.toString(), description.toString(), imageUrl.toString(), readMoreUrl.toString(), time.toString(), url.toString(), category)
