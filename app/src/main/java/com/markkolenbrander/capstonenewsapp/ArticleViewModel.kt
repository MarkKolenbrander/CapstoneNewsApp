package com.markkolenbrander.capstonenewsapp

import androidx.lifecycle.*
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.prefsstore.PrefsStore
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

const val BASE_URL = "https://newsapi.org/v2/"
const val API_TOKEN = "9ced23497a9d4184bffbe366d3a804d7"

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepo: NewsArticleRepo,
    private val prefsStore: PrefsStore
    ) : ViewModel() {

    class Factory(
        private val newsRepo: NewsArticleRepo,
        private val prefsStore: PrefsStore
    ): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleViewModel(newsRepo, prefsStore) as T
        }
    }

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        viewModelScope.launch(IO) {
            newsRepo
                .getNewsArticles()
                .onEach { newArticles ->
                    _articles.postValue(newArticles)
                }
                .collect()
        }
    }

    fun searchArticles(search: String){
        viewModelScope.launch(IO) {
            val filteredArticles = newsRepo.searchArticles("%$search%")
            _articles.postValue(CustomResult.Success(filteredArticles))
        }
    }

    private val _articles = MutableLiveData<CustomResult<List<Article>>>()
    val articles: LiveData<CustomResult<List<Article>>> = _articles

    val darkThemeEnabled = prefsStore.isNightMode().asLiveData()

    fun toggleNightMode(){
        viewModelScope.launch {
            prefsStore.toggleNightMode()
        }
    }
}