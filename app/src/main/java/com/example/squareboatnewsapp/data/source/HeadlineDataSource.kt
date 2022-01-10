package com.example.squareboatnewsapp.data.source

import androidx.paging.PagingSource
import com.example.squareboatnewsapp.data.network.NewsService
import javax.inject.Inject

/**
 * @Author: Akshay Sharma
 * @Date: 10/01/22
 */
class HeadlineDataSource @Inject constructor(
   private val newsService: NewsService
): PagingSource {


}