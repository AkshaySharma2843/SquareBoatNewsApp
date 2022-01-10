package com.example.squareboatnewsapp.data.source

import android.net.Uri
import com.example.squareboatnewsapp.BuildConfig
import com.example.squareboatnewsapp.data.network.NewsService
import com.example.squareboatnewsapp.utils.constants.UrlConstants
import javax.inject.Inject

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
class RemoteDataSource @Inject constructor(
    private val networkService: NewsService
) : BaseDataSource()
{

    suspend fun getHomeNews(pageSize: Int) = getResult {networkService.getNews(getHeadlinesUrl(pageSize))}

    private fun getHeadlinesUrl(pageSize: Int): String {
        val uri = Uri.Builder()
            .appendPath("v2")
            .appendPath("top-headlines")
            .appendQueryParameter("country", UrlConstants.countryCodeToCall)
            .appendQueryParameter("category", "business")
            .appendQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)
            .appendQueryParameter("pageSize", pageSize.toString())
            .build()
        return uri.toString()
    }


}