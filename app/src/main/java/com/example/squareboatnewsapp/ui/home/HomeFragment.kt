package com.example.squareboatnewsapp.ui.home

import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squareboatnewsapp.databinding.FragmentHomeBinding
import com.example.squareboatnewsapp.di.component.FragmentComponent
import com.example.squareboatnewsapp.ui.base.BaseFragment
import com.example.squareboatnewsapp.utils.common.PagingScroller
import com.example.squareboatnewsapp.utils.common.Resource
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private var pageCount = 0
    private var isScrolling = false
    var currentItems = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

    override fun setupView() {
        getLatestHeadlines()
        observeHeadLines()
        observeRecyclerViewScrolling()
    }

    private fun observeRecyclerViewScrolling() {
        val recyclerViewScroller = object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = linearLayoutManager.childCount
                totalItems = linearLayoutManager.itemCount
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition()
                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false
                    pageCount++
                    getLatestHeadlines()
                }
            }
        }
        binding.rvNews.addOnScrollListener(recyclerViewScroller)
    }

    private fun observeHeadLines() {
        viewModel.pagedHeadlineList.observe(viewLifecycleOwner, {newsResponse->
            when(newsResponse.status){
                Resource.Status.SUCCESS -> {
                    renderRecycler()
                }
                Resource.Status.ERROR -> {
                    viewModel.handleNetworkError()
                }
                Resource.Status.LOADING -> {

                }
            }
        })
    }

    private fun renderRecycler() {
    }

    private fun getLatestHeadlines() {
        viewModel.getLatestHeadLines(pageCount)
    }

}