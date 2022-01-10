package com.example.squareboatnewsapp.data.network

import com.example.squareboatnewsapp.data.model.NewsArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
interface NewsService {

    @GET
    suspend fun getNews(@Url url : String) : Response<NewsArticleResponse>
}