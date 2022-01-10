package com.example.squareboatnewsapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */

data class NewsArticleResponse(
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int?,
    @SerializedName("articles")
    @Expose
    val article: List<Article>

) {
    data class Article(
        @SerializedName("source")
        @Expose
        val source: NewsSource,
        @SerializedName("author")
        @Expose
        val author: String?,
        @SerializedName("title")
        @Expose
        val title: String?,
        @SerializedName("description")
        @Expose
        val description: String?,
        @SerializedName("url")
        @Expose
        val url: String?,
        @SerializedName("urlToImage")
        @Expose
        val urlToImage: Any?,
        @SerializedName("publishedAt")
        @Expose
        val publishedAt: String?,
        @SerializedName("content")
        @Expose
        val content: String?
    ) {
        data class NewsSource(
            @SerializedName("id")
            @Expose
            val id: Any?,
            @SerializedName("name")
            @Expose
            val name: String?
        )
    }
}
