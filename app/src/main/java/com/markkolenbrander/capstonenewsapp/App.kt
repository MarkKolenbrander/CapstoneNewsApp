package com.markkolenbrander.capstonenewsapp

import android.app.Application
import android.net.ConnectivityManager
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusChecker
import com.markkolenbrander.capstonenewsapp.networking.buildApiService
import com.markkolenbrander.capstonenewsapp.prefsstore.PrefsStore
import com.markkolenbrander.capstonenewsapp.prefsstore.PrefsStoreImpl
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepoImpl
import com.markkolenbrander.capstonenewsapp.views.NewsArticleDatabase

private const val STORE_NAME = "user_preferences"

class App : Application() {

    private val dataStore by preferencesDataStore(name = STORE_NAME)

    companion object{

        private lateinit var instance: App

        private val database: NewsArticleDatabase by lazy {
            NewsArticleDatabase.buildDatabase(instance)
        }

        private val networkStatusChecker by lazy {
            val connectivityManger = instance.getSystemService(ConnectivityManager::class.java)
            NetworkStatusChecker(connectivityManger)
        }

        private val newsApiService by lazy {
            buildApiService()
        }

        val newsRepo: NewsArticleRepo by lazy {
            NewsArticleRepoImpl(
                database.articleDao(),
                database.sourceDao() ,
                newsApiService,
                networkStatusChecker,
            )
        }

        val prefsStore: PrefsStore by lazy {
            PrefsStoreImpl(instance.dataStore)
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