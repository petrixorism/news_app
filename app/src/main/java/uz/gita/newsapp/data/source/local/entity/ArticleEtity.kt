package uz.gita.newsapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey
    val title: String,

    val author: String,

    val description: String,

    val imageUrl: String,

    val readMoreUrl: String,

    val time: String,

    val url: String,

    val category: String,

    var isFav: Boolean = false
)
