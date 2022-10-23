package com.markkolenbrander.capstonenewsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//private const val STORE_NAME = "user_preferences"



@HiltAndroidApp
class App : Application()

//{
//
//    private val dataStore by preferencesDataStore(name = STORE_NAME)
//
//    companion object{
//
//        private lateinit var instance: App
//
//        private val database: NewsArticleDatabase by lazy {
//            NewsArticleDatabase.buildDatabase(instance)
//        }
//
//        private val networkStatusChecker by lazy {
//            val connectivityManger = instance.getSystemService(ConnectivityManager::class.java)
//            NetworkStatusCheckerImpl(connectivityManger)
//        }
//
//        private val newsApiService by lazy {
//            buildApiService()
//        }
//
//        val newsRepo: NewsArticleRepo by lazy {
//            NewsArticleRepoImpl(
//                database.articleDao(),
//                database.sourceDao() ,
//                newsApiService,
//                networkStatusChecker,
//            )
//        }
//
//        val prefsStore: PrefsStore by lazy {
//            PrefsStoreImpl(instance.dataStore)
//        }
//
//        val gson by lazy {
//            Gson()
//        }
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//    }
//}