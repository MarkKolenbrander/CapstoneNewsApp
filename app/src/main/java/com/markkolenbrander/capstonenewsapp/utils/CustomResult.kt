package com.markkolenbrander.capstonenewsapp.utils

sealed class CustomResult<out T>{
//    object Loading : CustomResult()
    data class Success<out T>(val value: T): CustomResult<T>()
    data class Failure(val e: Exception) : CustomResult<Nothing>()
    data class NoInternet<out T>(val value: T) : CustomResult<T>()
}