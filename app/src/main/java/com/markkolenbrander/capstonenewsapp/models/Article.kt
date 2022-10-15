package com.markkolenbrander.capstonenewsapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.markkolenbrander.capstonenewsapp.database.converters.DataConverter
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @TypeConverters(DataConverter::class)
    @Json(name = "source") val source: Source,
    @Json(name = "author")val author: String? = null,
    @PrimaryKey
    @Json(name = "title")val title: String,
    @Json(name = "description")val description: String? = null,
    @Json(name = "url")val url: String? = null,
    @Json(name = "urlToImage")val urlToImage: String? = null,
    @Json(name = "publishedAt")val publishedAt: String? = null,
    @Json(name = "content")val content: String? = null,
) : Parcelable

