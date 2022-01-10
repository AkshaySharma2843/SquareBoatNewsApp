package com.example.squareboatnewsapp.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.squareboatnewsapp.data.model.Country
import com.example.squareboatnewsapp.data.model.NewsArticleResponse
import com.example.squareboatnewsapp.data.repo.NewsRepository
import com.example.squareboatnewsapp.ui.base.BaseViewModel

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
class MainViewModel(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    val toChangeCountry = MutableLiveData<Boolean>()
    val articleLiveData = MutableLiveData<NewsArticleResponse.Article>()
    var newsArticleResponse : NewsArticleResponse? = null
    var searchResultResponse = ArrayList<NewsArticleResponse.Article>()
    val searchResultResponseLiveData = MutableLiveData<ArrayList<NewsArticleResponse.Article>>()

    fun changeCountry() {
        toChangeCountry.postValue(true)
    }

    fun postDetailedArticle(article: NewsArticleResponse.Article) {
        articleLiveData.postValue(article)
    }

    fun filterData(filterKey: String) {
        searchResultResponse.clear()
        newsArticleResponse?.article?.forEach {
            if(it.title?.contains(filterKey) == true){
                searchResultResponse.add(it)
            }
        }
        searchResultResponseLiveData.postValue(searchResultResponse)
    }

    var countryList = ArrayList<Country>()

    init {
         countryList = arrayListOf(
            Country("India", "in", false),
            Country("USA", "us", false),
            Country("UK", "uk", false),
            Country("Sweden", "se", false),
            Country("Japan", "jp", false)

        )
    }
}