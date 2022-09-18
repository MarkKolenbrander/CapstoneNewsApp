package com.markkolenbrander.capstonenewsapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Base(
    @Json(name = "status") val status: String,
    @Json(name ="totalResults") val totalResults: Int,
    @Json(name ="articles") val articles: List<Article>
) : Parcelable