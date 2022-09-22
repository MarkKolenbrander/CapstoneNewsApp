package com.markkolenbrander.capstonenewsapp


import com.markkolenbrander.capstonenewsapp.models.Base
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getArticles(
        @Query("apiKey") token : String,
        @Query("country") country : String,
    ) : Base
}
