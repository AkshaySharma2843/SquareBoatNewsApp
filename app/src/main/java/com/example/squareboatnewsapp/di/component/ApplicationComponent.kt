package com.example.squareboatnewsapp.di.component

import com.example.squareboatnewsapp.NewsApplication
import com.example.squareboatnewsapp.data.network.NewsService
import com.example.squareboatnewsapp.data.repo.NewsRepository
import com.example.squareboatnewsapp.data.source.RemoteDataSource
import com.example.squareboatnewsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(newsApplication: NewsApplication)
    fun getNewsRepository() : NewsRepository

}