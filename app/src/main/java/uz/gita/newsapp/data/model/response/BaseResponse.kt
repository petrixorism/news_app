package uz.gita.newsapp.data.model.response

import uz.gita.newsapp.data.model.response.ArticleResponse


data class BaseResponse(
    val category: String,
    val data: List<ArticleResponse>
)