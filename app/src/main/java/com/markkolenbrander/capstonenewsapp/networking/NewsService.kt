package com.markkolenbrander.capstonenewsapp.networking


import com.markkolenbrander.capstonenewsapp.models.Base
import com.markkolenbrander.capstonenewsapp.models.Category
import com.markkolenbrander.capstonenewsapp.models.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

//    @GET("top-headlines")
//    suspend fun getArticles(
//        @Query("apiKey") token : String,
//        @Query("country") country : Country,
//        @Query("category") category: Category,
//    ) : Response<GetArticleResponse>

    @GET("top-headlines")
    suspend fun getArticles(
        @Query("apiKey") token : String,
        @Query("country") country : Country,
        @Query("category") category: Category,
    ) : Base
}
