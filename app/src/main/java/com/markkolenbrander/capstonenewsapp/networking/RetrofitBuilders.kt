package com.markkolenbrander.capstonenewsapp.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun buildClient(): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}

fun buildApiService(): NewsService = buildRetrofit().create(NewsService::class.java)
