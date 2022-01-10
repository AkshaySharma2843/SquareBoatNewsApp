package com.example.squareboatnewsapp.ui.home

import com.example.squareboatnewsapp.data.model.NewsArticleResponse

interface OnNewsClickListener {
    fun onNewsClick(article : NewsArticleResponse.Article)
}