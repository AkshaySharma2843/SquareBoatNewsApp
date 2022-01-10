package com.example.squareboatnewsapp.ui.main

import com.example.squareboatnewsapp.data.repo.NewsRepository
import com.example.squareboatnewsapp.ui.base.BaseViewModel

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
class MainViewModel(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    var pageSize = 0

    fun getNews() = newsRepository.getNews(pageSize)
}