package com.example.squareboatnewsapp.ui.home

import com.bumptech.glide.Glide
import com.example.squareboatnewsapp.data.model.NewsArticleResponse
import com.example.squareboatnewsapp.databinding.ItemNewsBinding
import com.example.squareboatnewsapp.ui.base.BaseAdapter

class HomeHeadlineViewHolder(private val binding: ItemNewsBinding) : BaseAdapter.BaseViewHolder(binding) {

    fun bindArticle(
        articleToBind: NewsArticleResponse.Article,
        newsClickListener: OnNewsClickListener
    ) {
        binding.tvTitle.text = articleToBind.title
        binding.tvNewsHeadline.text = articleToBind.description
        binding.tvTime.text = articleToBind.publishedAt
        Glide.with(binding.ivNews.context)
            .load(articleToBind.urlToImage)
            .into(binding.ivNews)

        binding.clNews.setOnClickListener {
            newsClickListener.onNewsClick(articleToBind)
        }

    }

}