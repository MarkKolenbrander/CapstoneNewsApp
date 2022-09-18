package com.markkolenbrander.capstonenewsapp

import android.app.Application
import com.markkolenbrander.capstonenewsapp.networking.RemoteApi
import com.markkolenbrander.capstonenewsapp.networking.buildApiService

class App : Application() {

    private lateinit var instance: App

    companion object {
        private val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}