package com.markkolenbrander.capstonenewsapp.networking

import com.markkolenbrander.capstonenewsapp.NewsService
import com.markkolenbrander.capstonenewsapp.models.Base
import com.markkolenbrander.capstonenewsapp.models.Failure
import com.markkolenbrander.capstonenewsapp.models.Success

const val BASE_URL = "https://newsapi.org/v2/"
const val API_TOKEN = "9ced23497a9d4184bffbe366d3a804d7"

class RemoteApi (private val newsService: NewsService) {

    suspend fun getArticles()
            : com.markkolenbrander.capstonenewsapp.models.Result<Base> = try {
        val result = newsService.getArticles(API_TOKEN,"us")
        Success(data = result)
    }catch (error: Throwable) {
        Failure(error)
    }
}