package com.example.squareboatnewsapp.data.source

import com.example.squareboatnewsapp.data.network.NewsService
import javax.inject.Inject

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
class RemoteDataSource @Inject constructor(
    private val networkService: NewsService
) : BaseDataSource()
{

    suspend fun getHomeNews(pageSize: Int) = getResult {networkService.getNews(pageSize.toString())}

}