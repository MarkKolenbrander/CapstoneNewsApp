package com.markkolenbrander.capstonenewsapp

import androidx.lifecycle.*
import com.markkolenbrander.capstonenewsapp.models.*
import com.markkolenbrander.capstonenewsapp.networking.NewsService
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val BASE_URL = "https://newsapi.org/v2/"
const val API_TOKEN = "9ced23497a9d4184bffbe366d3a804d7"

class ArticleViewModel(private val newsService: NewsService) : ViewModel() {

    class Factory(
        private val newsService: NewsService,
    ): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleViewModel(newsService) as T
        }
    }

    private val _articleLiveData = MutableLiveData< CustomResult<ArticlesResponse>>()
    val articleLiveData: LiveData<CustomResult<ArticlesResponse>> = _articleLiveData

    init {
        viewModelScope.launch(IO) {
            try {
                val response = newsService.getArticles(API_TOKEN, Country.NL, Category.GENERAL)
                withContext(Main){
                    _articleLiveData.value = CustomResult.Success(response)
                }
            } catch (e: Exception){
                _articleLiveData.value = CustomResult.Failure(e)
            }
        }
    }
}