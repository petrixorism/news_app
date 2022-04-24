package uz.gita.newsapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.data.source.local.entity.ArticleEntity

@Dao
interface NewsDao : BaseDao<ArticleEntity> {

    @Query("SELECT * FROM ArticleEntity WHERE category =:category")
    fun getNewsByCategory(category: String): LiveData<List<ArticleData>>

    @Query("SELECT * FROM ArticleEntity WHERE category =:category")
    fun getNewsByCategories(category: String):List<ArticleData>

    @Update
    fun updateArticle(articleData: ArticleEntity)
}