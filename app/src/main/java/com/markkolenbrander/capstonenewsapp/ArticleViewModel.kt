package com.markkolenbrander.capstonenewsapp

import androidx.lifecycle.*
import com.markkolenbrander.capstonenewsapp.models.*
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.utils.CustomResult

const val BASE_URL = "https://newsapi.org/v2/"
const val API_TOKEN = "9ced23497a9d4184bffbe366d3a804d7"

class ArticleViewModel(private val newsRepo: NewsArticleRepo) : ViewModel() {

    class Factory(
        private val newsRepo: NewsArticleRepo,
    ): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleViewModel(newsRepo) as T
        }
    }

    val articles: LiveData<CustomResult<List<Article>>> =
        newsRepo.getNewsArticles().asLiveData()

//    private val _articleLiveData = MutableLiveData< CustomResult<ArticlesResponse>>()
//    val articleLiveData: LiveData<CustomResult<ArticlesResponse>> = _articleLiveData


//    init {
//        viewModelScope.launch(IO) {
//            try {
//                val response = newsService.getArticles(API_TOKEN, Country.NL, Category.GENERAL)
//                withContext(Main){
//                    _articleLiveData.value = CustomResult.Success(response)
//                }
//            } catch (e: Exception){
//                _articleLiveData.value = CustomResult.Failure(e)
//            }
//        }
//    }
}