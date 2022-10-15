package com.markkolenbrander.capstonenewsapp.prefsstore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    fun isNightMode(): Flow<Boolean>

    suspend fun toggleNightMode()

    fun isWifiEnabled(): Flow<Boolean>

    suspend fun toggleDownloadOverWifiOnly()
}