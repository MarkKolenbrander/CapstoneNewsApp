package com.markkolenbrander.capstonenewsapp.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.markkolenbrander.capstonenewsapp.models.Source
import com.markkolenbrander.capstonenewsapp.App


class DataConverter {


    @TypeConverter
    fun fromSources(value: Source): String = App.gson.toJson(value)

    @TypeConverter
    fun toSources(json: String): Source {
        val listType = object : TypeToken<Source>() {}.type

        return App.gson.fromJson(json, listType)
    }

}