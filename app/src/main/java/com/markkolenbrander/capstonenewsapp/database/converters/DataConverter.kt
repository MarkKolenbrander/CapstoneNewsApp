package com.markkolenbrander.capstonenewsapp.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.markkolenbrander.capstonenewsapp.models.Source

class DataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromSources(value: Source): String = gson.toJson(value)

    @TypeConverter
    fun toSources(json: String): Source {
        val listType = object : TypeToken<Source>() {}.type

        return gson.fromJson(json, listType)
    }

}