package com.markkolenbrander.capstonenewsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val id: String? = null,
    val name: String,
    val description: String,
    val url: String,
    val category: Category,
    val language: Language,
    val country: Country,
) : Parcelable

