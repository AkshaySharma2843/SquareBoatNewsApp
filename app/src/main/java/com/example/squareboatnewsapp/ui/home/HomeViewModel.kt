package com.example.squareboatnewsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.squareboatnewsapp.data.model.NewsArticleResponse
import com.example.squareboatnewsapp.data.repo.NewsRepository
import com.example.squareboatnewsapp.ui.base.BaseViewModel
import com.example.squareboatnewsapp.utils.common.Resource

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
class HomeViewModel(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    fun getLatestHeadlines(pageSize: Int) = newsRepository.getHeadlines(pageSize)

}