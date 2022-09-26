package com.markkolenbrander.capstonenewsapp.utils

sealed class CustomResult<out T>{
    data class Success<out T>(val value: T): CustomResult<T>()
    data class Failure(val e: Exception) : CustomResult<Nothing>()
}

