package com.markkolenbrander.capstonenewsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.markkolenbrander.capstonenewsapp.models.Article

class ArticleDataManager(application: Application) : AndroidViewModel(application) {

    //Todo: Couldn't get the shared preferences working

//    private val context = application.applicationContext
//    private val newsService: NewsService = InMemoryNewsServiceImpl()


//    fun saveArticleList(articles: ArrayList<Article?>){
//        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
//        val gson = Gson()
//        val json: String = gson.toJson(articles)
//        sharedPrefs.putString("articles", json)
//        sharedPrefs.apply()
//    }

//    fun readArticles(): ArrayList<ArticleList> {
//        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
//        val contents = sharedPrefs.all
//        val articlesList = ArrayList<ArticleList>()
//
//        for (article in contents){
//            val articleItems = ArrayList(article.value as HashSet<*>)
//            val articleList = ArticleList(article.key, articleItems.toString())
//            articlesList.add(articleList)
//        }
//        return articlesList
//    }

}