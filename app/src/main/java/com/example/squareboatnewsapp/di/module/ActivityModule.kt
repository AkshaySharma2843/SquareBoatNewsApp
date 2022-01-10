package com.example.squareboatnewsapp.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squareboatnewsapp.data.repo.NewsRepository
import com.example.squareboatnewsapp.ui.base.BaseActivity
import com.example.squareboatnewsapp.ui.main.MainViewModel
import com.example.squareboatnewsapp.utils.common.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
@Module
class ActivityModule(private val activity: BaseActivity<*, *>){

    @Provides
    fun provideContext(
    ) : Context = activity

    @Provides
    fun provideLinearLayoutManager() : LinearLayoutManager= LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        newsRepository: NewsRepository
    ): MainViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(newsRepository)
        })[MainViewModel::class.java]
}