package com.markkolenbrander.capstonenewsapp

class ArticleViewModelOldTest{

//    @ExperimentalCoroutinesApi
//    @get:Rule
//    var coroutineTestRule = CoroutinesTestRule()
//
//    //is a rule that swaps out that executor and replaces it with synchronous one
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Test
//    fun fetch_articles_returns_data() = runBlocking {
//        val mockkNewsRepo = mockk<NewsArticleRepoImpl>()
//        val mockkPrefsStore = mockk<PrefsStore>()
//
//        mockkObject(NewsArticleRepoImpl)
//
//        coEvery { mockkNewsRepo.getNewsArticles() } returns flow{
//            CustomResult.Success(Article(
//                Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
//                "author",
//                "title" , "description", "url", "urlImage", "published", "content",
//            ))
//        }
//
//        val viewModel = ArticleViewModel(mockkNewsRepo, mockkPrefsStore)
//        viewModel.fetchArticles()
//
//        assertEquals( Article(
//            Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
//            "author",
//            "title" , "description", "url", "urlImage", "published", "content",
//        ), viewModel.articles)
//    }

//    @Test
//    fun searchArticles_gives_back_list_of_articles() = runBlocking {
//
//        val mockkNewsRepo = mockk<NewsArticleRepoImpl>()
//        val mockkPrefsStore = mockk<PrefsStore>()
//        val viewModel = ArticleViewModel(mockkNewsRepo, mockkPrefsStore)
//
//        val expectedListOfArticles = listOf<Article>(
//            Article(
//                Source("id","name","description","url", Category.GENERAL, Language.NL, Country.NL),
//                "author",
//                "title" , "description", "url", "urlImage", "published", "content",
//            )
//        )
//        coEvery { mockkNewsRepo.searchArticles("name") } returns expectedListOfArticles
//
//        val result = viewModel.searchArticles("name")
//        assertEquals(CustomResult.Success(expectedListOfArticles), result)
//
//    }

}