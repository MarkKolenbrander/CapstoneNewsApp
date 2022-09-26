package com.markkolenbrander.capstonenewsapp

import androidx.lifecycle.*
import com.markkolenbrander.capstonenewsapp.models.*
import com.markkolenbrander.capstonenewsapp.networking.NewsService
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import kotlinx.coroutines.launch

const val BASE_URL = "https://newsapi.org/v2/"
const val API_TOKEN = "9ced23497a9d4184bffbe366d3a804d7"

class ArticleViewModel(private val newsService: NewsService) : ViewModel() {

    class Factory(
        private val newsService: NewsService,
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleViewModel(newsService) as T
        }
    }

    private val _base = MutableLiveData<Base>()
    val base: LiveData<Base> = _base


    init {
        viewModelScope.launch {
            try {
                val response = newsService.getArticles(API_TOKEN, Country.NL, Category.GENERAL)
                _base.value = response

            } catch (e: Exception){
                CustomResult.Failure(e)
            }
        }

    }

}