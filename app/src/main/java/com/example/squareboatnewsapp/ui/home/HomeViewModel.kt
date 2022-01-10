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

    val pagedHeadlineList = MutableLiveData<Resource<List<NewsArticleResponse.Article>>>()
    private var headlineArrayList = ArrayList<NewsArticleResponse.Article>()

    fun getLatestHeadLines(pageSize: Int) {
        Transformations.switchMap(newsRepository.getNews(pageSize)) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    pagedHeadlineList.postValue(Resource.loading())
                    return@switchMap pagedHeadlineList
                }
                Resource.Status.SUCCESS -> {
                    it.data?.article?.let {listOfData->
                        headlineArrayList.addAll(listOfData)
                        pagedHeadlineList.postValue(Resource.success(headlineArrayList))
                    }
                    return@switchMap pagedHeadlineList

                }
                Resource.Status.ERROR -> {
                    pagedHeadlineList.postValue(Resource.error(""))
                    return@switchMap pagedHeadlineList
                }
            }
        }
    }
}