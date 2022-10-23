package com.markkolenbrander.capstonenewsapp.di

import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusChecker
import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusCheckerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkCheckerModule {

    @Binds
    @Singleton
    abstract fun bindNetworkChecker(networkStatusCheckerImpl: NetworkStatusCheckerImpl) : NetworkStatusChecker

}