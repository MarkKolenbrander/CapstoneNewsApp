package com.markkolenbrander.capstonenewsapp

import com.markkolenbrander.capstonenewsapp.database.dao.ArticleDao
import com.markkolenbrander.capstonenewsapp.database.dao.SourceDao
import com.markkolenbrander.capstonenewsapp.models.*
import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusChecker
import com.markkolenbrander.capstonenewsapp.networking.NewsService
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepoImpl
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsArticleRepoTests {

    private val mockArticleDao = mockk<ArticleDao>()
    private val mockSourceDao = mockk<SourceDao>()
    private val mockApiService = mockk<NewsService>()
    private val mockNetworkStatusChecker = mockk<NetworkStatusChecker>()


    private val sut = NewsArticleRepoImpl(mockArticleDao, mockSourceDao, mockApiService, mockNetworkStatusChecker)

    @Test
    fun `get articles returns data`() = runBlocking {
//        val expectedListOfArticles = emptyList<Article>()
        val expectedListOfArticles = listOf<Article>(
            Article(
            Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
            "author",
            "title" , "description", "url", "urlImage", "published", "content",
            )
        )
        coEvery {mockArticleDao.getArticles()} returns expectedListOfArticles

        val result = sut.getNewsArticles()
            .first()

        assertEquals(CustomResult.Success(expectedListOfArticles), result)
        coVerify (exactly = 0){ mockApiService.getArticles(API_TOKEN, Country.NL,Category.GENERAL ) }
    }

    @Test fun `a list of articles is returned`() = runBlocking {

        coEvery { mockApiService.getArticles(API_TOKEN, Country.NL, Category.GENERAL) } returns ArticlesResponse(
            "status",1, listOf(Article(
                Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
                "author",
                "title" , "description", "url", "urlImage", "published", "content",
            )
            )
        )
        val articles = mockApiService.getArticles(API_TOKEN,Country.NL, Category.GENERAL)
        assertEquals(1, articles.articles.size)

//        val expectedListOfArticles = listOf<Article>(
//            Article(
//                Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
//                "author",
//                "title" , "description", "url", "urlImage", "published", "content",
//            )
//        )
//
//        val articlesNew = sut.getNewsArticles().first()
//        assertEquals((CustomResult.Success(expectedListOfArticles.size)), articlesNew)

    }


}