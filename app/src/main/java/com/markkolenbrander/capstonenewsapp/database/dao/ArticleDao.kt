package com.markkolenbrander.capstonenewsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markkolenbrander.capstonenewsapp.models.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articles: List<Article>)

    @Query("SELECT * FROM articles WHERE title LIKE :search")
    suspend fun searchArticles(search: String) : List<Article>

    @Query("DELETE FROM articles")
    suspend fun deleteArticles()
}