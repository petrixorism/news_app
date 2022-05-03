package uz.gita.newsapp.data.model.response


data class BaseResponse(
    val category: String,
    val data: List<ArticleResponse>
)