package com.example.squareboatnewsapp.ui.home

import android.view.ViewGroup
import com.example.squareboatnewsapp.data.model.NewsArticleResponse
import com.example.squareboatnewsapp.databinding.ItemNewsBinding
import com.example.squareboatnewsapp.ui.base.BaseAdapter

class HomeHeadlinesAdapter(private val newsClickListener: OnNewsClickListener) :
    BaseAdapter<HomeHeadlineViewHolder>() {

    private var listOfData: ArrayList<NewsArticleResponse.Article> = ArrayList()

    fun submitList(listOfData: ArrayList<NewsArticleResponse.Article>) {
        this.listOfData = listOfData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHeadlineViewHolder =
        HomeHeadlineViewHolder(parent.inflateBinding(ItemNewsBinding::inflate))

    override fun onBindViewHolder(holder: HomeHeadlineViewHolder, position: Int) {
        val articleToBind = listOfData[position]
        holder.bindArticle(articleToBind, newsClickListener)
    }

    override fun getItemCount(): Int = listOfData.size
}