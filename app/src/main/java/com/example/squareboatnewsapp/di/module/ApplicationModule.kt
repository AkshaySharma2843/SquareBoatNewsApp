package com.example.squareboatnewsapp.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.squareboatnewsapp.BuildConfig
import com.example.squareboatnewsapp.NewsApplication
import com.example.squareboatnewsapp.data.network.Networking
import com.example.squareboatnewsapp.data.network.NewsService
import com.example.squareboatnewsapp.data.repo.NewsRepository
import com.example.squareboatnewsapp.data.source.RemoteDataSource
import com.example.squareboatnewsapp.utils.constants.PreferenceConstants.NEWS_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
@Module
class ApplicationModule(private val application: NewsApplication) {

    @Provides
    fun provideContext() : Context = application

    @Provides
    fun provideNetworkService(): NewsService = Networking.create(BuildConfig.BASE_URL)

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = application.getSharedPreferences(NEWS_PREFERENCES_NAME, Context.MODE_PRIVATE)


    @Provides
    fun provideRemoteSource(newsService: NewsService) : RemoteDataSource = RemoteDataSource(newsService)

    @Provides
    fun provideNewsRepository(remoteDataSource: RemoteDataSource) : NewsRepository = NewsRepository(remoteDataSource)
}