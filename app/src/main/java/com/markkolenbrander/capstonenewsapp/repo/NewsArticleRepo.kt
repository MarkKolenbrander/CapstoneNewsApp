package com.markkolenbrander.capstonenewsapp.repo

import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import kotlinx.coroutines.flow.Flow

interface NewsArticleRepo {
    fun getNewsArticles() : Flow<CustomResult<List<Article>>>

    suspend fun searchArticles(search: String) : List<Article>

}