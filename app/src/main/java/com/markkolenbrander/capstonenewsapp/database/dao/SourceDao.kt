package com.markkolenbrander.capstonenewsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markkolenbrander.capstonenewsapp.models.Source

@Dao
interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSources(source: Source)

    @Query("DELETE FROM sources")
    suspend fun deleteSources()
}