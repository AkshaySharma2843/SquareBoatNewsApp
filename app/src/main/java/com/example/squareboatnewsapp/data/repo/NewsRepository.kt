package com.example.squareboatnewsapp.data.repo

import com.example.squareboatnewsapp.data.source.RemoteDataSource
import com.example.squareboatnewsapp.utils.strategy.performNetworkOperation
import javax.inject.Inject

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun getHeadlines(pageSize : Int) = performNetworkOperation {
        remoteDataSource.getHomeNews(pageSize)
    }

}