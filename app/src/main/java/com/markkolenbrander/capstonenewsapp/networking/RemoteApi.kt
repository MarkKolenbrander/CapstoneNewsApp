package com.markkolenbrander.capstonenewsapp.networking

import com.markkolenbrander.capstonenewsapp.models.*

const val BASE_URL = "https://newsapi.org/v2/"
const val API_TOKEN = "9ced23497a9d4184bffbe366d3a804d7"

class RemoteApi (private val newsService: NewsService) {

//    suspend fun getArticles()
//            : Result<Base> = try {
//        val articles = newsService.getArticles(API_TOKEN, Country.NL, Category.GENERAL)
//        Result.Success(articles)
//    }catch (e: Exception) {
//        Result.Failure(e)
//    }

}