package com.markkolenbrander.capstonenewsapp

import android.app.Application
import com.google.gson.Gson
import com.markkolenbrander.capstonenewsapp.networking.buildApiService
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepoImpl
import com.markkolenbrander.capstonenewsapp.views.NewsArticleDatabase

class App : Application() {
    companion object{
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


        val gson by lazy {
            Gson()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}