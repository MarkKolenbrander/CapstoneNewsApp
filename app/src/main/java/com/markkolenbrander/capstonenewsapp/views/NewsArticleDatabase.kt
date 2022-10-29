package com.markkolenbrander.capstonenewsapp.views

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.markkolenbrander.capstonenewsapp.database.converters.DataConverter
import com.markkolenbrander.capstonenewsapp.database.dao.ArticleDao
import com.markkolenbrander.capstonenewsapp.database.dao.SourceDao
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.models.Source

private const val DATABASE_VERSION = 1

@Database (entities = [Article::class, Source::class] , version = DATABASE_VERSION)

@TypeConverters(DataConverter::class)
abstract class NewsArticleDatabase : RoomDatabase() {

//    companion object{
//        private const val DATABASE_NAME = "NewsArticles"
//
//        fun buildDatabase(context: Context): NewsArticleDatabase {
//            return Room.databaseBuilder(
//                context,
//                NewsArticleDatabase::class.java,
//                DATABASE_NAME,
//            )
//                .allowMainThreadQueries()
//                .build()
//        }
//    }

    abstract fun articleDao(): ArticleDao

    abstract fun sourceDao(): SourceDao

}