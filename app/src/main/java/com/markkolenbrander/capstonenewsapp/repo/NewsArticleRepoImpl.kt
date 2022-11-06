package com.markkolenbrander.capstonenewsapp.repo

import com.markkolenbrander.capstonenewsapp.API_TOKEN
import com.markkolenbrander.capstonenewsapp.database.dao.ArticleDao
import com.markkolenbrander.capstonenewsapp.database.dao.SourceDao
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.models.Category
import com.markkolenbrander.capstonenewsapp.models.Country
import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusChecker
import com.markkolenbrander.capstonenewsapp.networking.NewsService
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsArticleRepoImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val sourceDao: SourceDao,
    private val newsApiService: NewsService,
    private val networkStatusChecker: NetworkStatusChecker,
) : NewsArticleRepo {

    override fun getNewsArticles(): Flow<CustomResult<List<Article>>> {

        return flow {

            val newsArticlesFromLocalDb = articleDao.getArticles()

            emit(CustomResult.Success(newsArticlesFromLocalDb))

            try {
                if (networkStatusChecker.hasInternetConnection()){
                    val newsArticlesFromNetwork = newsApiService
                        .getArticles(API_TOKEN, Country.NL, Category.ENTERTAINMENT)
                        .articles

                    emit(CustomResult.Success(newsArticlesFromNetwork))

                    if (newsArticlesFromNetwork.isNotEmpty()){
                        articleDao.deleteArticles()
                        articleDao.addArticles(newsArticlesFromNetwork)
                        newsArticlesFromNetwork.forEach { article ->
                            sourceDao.addSources(article.source)
                        }
                    }
                }else {
                    emit(CustomResult.NoInternet(newsArticlesFromLocalDb))
                }
            }catch (e: Exception){
                emit(CustomResult.Failure(e))
            }
        }
    }

    override suspend fun searchArticles(search: String): List<Article> {
        return articleDao.searchArticles(search)
    }
}