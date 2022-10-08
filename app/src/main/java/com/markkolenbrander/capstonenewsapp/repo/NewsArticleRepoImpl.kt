package com.markkolenbrander.capstonenewsapp.repo

import android.util.Log
import com.markkolenbrander.capstonenewsapp.API_TOKEN
import com.markkolenbrander.capstonenewsapp.database.dao.ArticleDao
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.models.Category
import com.markkolenbrander.capstonenewsapp.models.Country
import com.markkolenbrander.capstonenewsapp.networking.NewsService
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsArticleRepoImpl(
    private val articleDao: ArticleDao,
    private val newsApiService: NewsService,
) : NewsArticleRepo {

    override fun getNewsArticles(): Flow<CustomResult<List<Article>>> {
        return flow {
            val newsArticlesFromLocalDb = articleDao.getArticles()

            emit(CustomResult.Success(newsArticlesFromLocalDb))

            try {
                val newsArticlesFromNetwork = newsApiService
                    .getArticles(API_TOKEN, Country.NL, Category.GENERAL)
                    .articles

                emit(CustomResult.Success(newsArticlesFromNetwork))

                if (newsArticlesFromNetwork.isNotEmpty()){
                    articleDao.addArticles(newsArticlesFromNetwork)
                }
            }catch (e: Exception){
                Log.e(TAG, e.toString())
            }
        }
    }

    override suspend fun searchArticles(search: String): List<Article> {
        return articleDao.searchArticles(search)
    }

    companion object {
        private const val TAG = "ArticleRepoImpl"
    }
}