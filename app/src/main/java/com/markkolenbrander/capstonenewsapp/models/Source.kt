package com.markkolenbrander.capstonenewsapp.models

data class Source(
    val id: String? = null,
    val name: String,
    val description: String,
    val url: String,
    val category: Category,
    val language: Language,
    val country: Country,
)


