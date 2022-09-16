package com.markkolenbrander.capstonenewsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.markkolenbrander.capstonenewsapp.models.Article

class ArticleDataManager(application: Application) : AndroidViewModel(application){

    private val context = application.applicationContext

    private val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    private val gson = Gson()

    fun saveArticles(articles: ArrayList<Article?>){
        val articlesJson = gson.toJson(articles)
        sharedPrefs.edit().putString(PREFS_KEY_ARTICLES, articlesJson).apply()
    }

    fun fetchArticles(): ArrayList<Article?>{
        val articleJson = sharedPrefs.getString(PREFS_KEY_ARTICLES, "")
        val articleListTypeToken = object : TypeToken<ArrayList<Article?>>(){ }
        return gson.fromJson(articleJson, articleListTypeToken.type)
    }

    companion object{
        private const val PREFS_KEY_ARTICLES = "articles"
    }

}