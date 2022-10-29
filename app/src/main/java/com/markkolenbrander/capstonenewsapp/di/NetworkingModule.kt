package com.markkolenbrander.capstonenewsapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.markkolenbrander.capstonenewsapp.BASE_URL
import com.markkolenbrander.capstonenewsapp.networking.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun buildClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
            .build()

    @Provides
    @Singleton
    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(buildClient())
            .baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    @Provides
    @Singleton
    fun buildApiService(): NewsService = buildRetrofit().create(NewsService::class.java)

    @Provides
    @Singleton
    fun providesConnectivityManager(@ApplicationContext context: Context) : ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)

    }

    @Provides
    @Singleton
    fun providesGson () : Gson {
        return Gson()
    }


}