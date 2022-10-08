package com.markkolenbrander.capstonenewsapp.prefsstore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    fun isNightMode(): Flow<Boolean>

    suspend fun toogleNightMode()
}