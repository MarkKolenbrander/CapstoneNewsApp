package com.markkolenbrander.capstonenewsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.models.Base
import com.markkolenbrander.capstonenewsapp.models.Category
import com.markkolenbrander.capstonenewsapp.models.Country
import com.markkolenbrander.capstonenewsapp.networking.NewsService
import kotlinx.coroutines.launch

const val BASE_URL = "https://newsapi.org/v2/"
const val API_TOKEN = "9ced23497a9d4184bffbe366d3a804d7"

class ArticleViewModel(private val newsService: NewsService) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>(emptyList())
    val articles: LiveData<List<Article>> = _articles

//    private val _status = MutableLiveData<Result<T>>()


    fun getArticles(){
        viewModelScope.launch {
            try {
                _articles.value = listOf(newsService.getArticles(API_TOKEN, Country.NL, Category.GENERAL))
            } catch (e: Exception){
                _articles.value = listOf()
            }
        }
    }

}