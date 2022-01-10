package com.example.squareboatnewsapp.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squareboatnewsapp.data.repo.NewsRepository
import com.example.squareboatnewsapp.ui.base.BaseFragment
import com.example.squareboatnewsapp.ui.details.DetailsViewModel
import com.example.squareboatnewsapp.ui.home.HomeViewModel
import com.example.squareboatnewsapp.ui.main.MainViewModel
import com.example.squareboatnewsapp.ui.search.SearchViewModel
import com.example.squareboatnewsapp.utils.common.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
@Module
class FragmentModule(private val fragment : BaseFragment<*,*>) {

    @Provides
    @Singleton
    fun provideLinearLayoutManager() : LinearLayoutManager = LinearLayoutManager(fragment.activity)

    @Provides
    fun provideHomeViewModel(
        newsRepository: NewsRepository
    ): HomeViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(newsRepository)
        })[HomeViewModel::class.java]

    @Provides
    fun provideSearchViewModel(
        newsRepository: NewsRepository
    ): SearchViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(SearchViewModel::class) {
            SearchViewModel(newsRepository)
        })[SearchViewModel::class.java]

    @Provides
    fun provideDetailsViewModel(
        newsRepository: NewsRepository
    ): DetailsViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(DetailsViewModel::class) {
            DetailsViewModel(newsRepository)
        })[DetailsViewModel::class.java]

    @Provides
    fun provideMainViewModel(
        newsRepository: NewsRepository
    ): MainViewModel = ViewModelProvider(
        fragment.activity!!, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(newsRepository)
        })[MainViewModel::class.java]
}