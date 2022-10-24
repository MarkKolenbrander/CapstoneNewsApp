package com.markkolenbrander.capstonenewsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.markkolenbrander.capstonenewsapp.models.*
import com.markkolenbrander.capstonenewsapp.prefsstore.PrefsStore
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepoImpl
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticleViewModelTest{

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var objectUnderTest: ArticleViewModel
//    private lateinit var funService: FakeService
    private val mockkNewsRepo = mockk<NewsArticleRepoImpl>()
    private val mockkPrefsStore = mockk<PrefsStore>()

    @Before
    fun setUp() {
        objectUnderTest = ArticleViewModel(newsRepo = mockkNewsRepo, prefsStore = mockkPrefsStore )
    }

    @Test
    fun fetch_articles_returns_data() = runBlocking {

//        mockkObject(NewsArticleRepoImpl)

        coEvery { mockkNewsRepo.getNewsArticles() } returns flow{
            CustomResult.Success(
                Article(
                Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
                "author",
                "title" , "description", "url", "urlImage", "published", "content",
            )
            )
        }
        objectUnderTest.fetchArticles()

        assertEquals( Article(
            Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
            "author",
            "title" , "description", "url", "urlImage", "published", "content",
        ), objectUnderTest.articles)
    }

}