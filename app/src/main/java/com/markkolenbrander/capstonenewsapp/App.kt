package com.markkolenbrander.capstonenewsapp

import android.app.Application
import com.google.gson.Gson
import com.markkolenbrander.capstonenewsapp.networking.buildApiService
import com.markkolenbrander.capstonenewsapp.prefsstore.PrefsStore
import com.markkolenbrander.capstonenewsapp.prefsstore.PrefsStoreImpl
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepoImpl
import com.markkolenbrander.capstonenewsapp.views.NewsArticleDatabase

class App : Application() {
    companion object{
        private const val KEY_PREFERENCES = "newsAppPreferences"

        private lateinit var instance: App
        private val database: NewsArticleDatabase by lazy {
            NewsArticleDatabase.buildDatabase(instance)
        }
        private val newsApiService by lazy {
            buildApiService()
        }

        val newsRepo: NewsArticleRepo by lazy {
            NewsArticleRepoImpl(database.articleDao(), newsApiService)
        }

        val prefsStore: PrefsStore by lazy {
            PrefsStoreImpl(instance)
        }

//        val sharedPrefs: SharedPreferences by lazy {
//            instance.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
//        }

        val gson by lazy {
            Gson()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}