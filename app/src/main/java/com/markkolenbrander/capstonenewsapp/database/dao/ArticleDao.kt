package com.markkolenbrander.capstonenewsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.markkolenbrander.capstonenewsapp.models.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<Article>

    @Insert(onConflict = REPLACE)
    suspend fun addArticles(article: List<Article>)
}