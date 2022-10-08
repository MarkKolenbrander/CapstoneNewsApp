package com.markkolenbrander.capstonenewsapp

import androidx.lifecycle.*
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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

    init {
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

    //--------------------------------------------------------------------------
//    //TODO: Toggle Darkmode
//    private val _darkThemeEnabled = MutableLiveData<Boolean>()
//    val darkThemeEnabled: LiveData<Boolean> = _darkThemeEnabled
//
//    fun toggleNightMode(){
//        viewModelScope.launch {
//            val darkThemeEnabled = _darkThemeEnabled.value!!
//            sharedPrefs.setDarkThemeEnabled(!darkThemeEnabled)
//            _darkThemeEnabled.value = !darkThemeEnabled
//        }
//    }

    //--------------------------------------------------------------------------


//    val articles: LiveData<CustomResult<List<Article>>> =
//        newsRepo.getNewsArticles().asLiveData()




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