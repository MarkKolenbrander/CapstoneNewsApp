package com.markkolenbrander.capstonenewsapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "category") val category: Category? = null,
    @Json(name = "language") val language: Language? = null,
    @Json(name = "country") val country: Country? = null,
) : Parcelable

