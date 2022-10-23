package com.markkolenbrander.capstonenewsapp.prefsstore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class PrefsStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : PrefsStore {

    private object PreferencesKeys{
        val NIGHT_MODE_KEY = booleanPreferencesKey("dark_theme_enabled")
        val DOWNLOAD_WIFI = booleanPreferencesKey("has_wifi_enabled")
    }

    override fun isNightMode() = dataStore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { it[PreferencesKeys.NIGHT_MODE_KEY]  ?: false }

    override suspend fun toggleNightMode() {
        dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false )
        }
    }

    override fun isWifiEnabled() = dataStore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { it[PreferencesKeys.DOWNLOAD_WIFI]  ?: false }

    override suspend fun toggleDownloadOverWifiOnly() {
       dataStore.edit {
           it[PreferencesKeys.DOWNLOAD_WIFI] = !(it[PreferencesKeys.DOWNLOAD_WIFI] ?: false )
       }
    }



}