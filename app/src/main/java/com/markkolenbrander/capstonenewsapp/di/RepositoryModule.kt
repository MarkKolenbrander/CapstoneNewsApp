package com.markkolenbrander.capstonenewsapp.di

import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(newsArticleRepoImpl: NewsArticleRepoImpl) : NewsArticleRepo

}