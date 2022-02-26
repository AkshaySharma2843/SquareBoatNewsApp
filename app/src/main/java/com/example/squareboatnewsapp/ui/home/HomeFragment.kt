package com.example.squareboatnewsapp.ui.home

import android.view.View
import android.widget.AbsListView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squareboatnewsapp.R
import com.example.squareboatnewsapp.data.model.NewsArticleResponse
import com.example.squareboatnewsapp.databinding.FragmentHomeBinding
import com.example.squareboatnewsapp.di.component.FragmentComponent
import com.example.squareboatnewsapp.ui.base.BaseFragment
import com.example.squareboatnewsapp.ui.main.MainActivity
import com.example.squareboatnewsapp.ui.main.MainViewModel
import com.example.squareboatnewsapp.utils.common.Resource
import com.example.squareboatnewsapp.utils.constants.UrlConstants
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(FragmentHomeBinding::inflate),
    View.OnClickListener, OnNewsClickListener {

    private var pageCount = 10
    private var isScrolling = false
    var currentItems = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0
    private var newsArticleList = ArrayList<NewsArticleResponse.Article>()
    private var headlinesAdapter = HomeHeadlinesAdapter(this)
    private var navController: NavController? = null

    @Inject
    lateinit var mainViewModel: MainViewModel

    var linearLayoutManager: LinearLayoutManager? = null


    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        navController = NavHostFragment.findNavController(this)
        setClickListeners()
        setupRecycler()
        getLatestHeadlines()
        observeRecyclerViewScrolling()
        observeCountryRefresh()
    }


    private fun setClickListeners() {
        binding.etSearch.setOnClickListener(this)
    }

    private fun observeCountryRefresh() {
        UrlConstants.toRefreshData.observe(viewLifecycleOwner) {
            if (it) {
                pageCount = 10
                newsArticleList.clear()
                getLatestHeadlines()
                mainViewModel.changeCountry()
                UrlConstants.toRefreshData.postValue(false)
            }
        }
    }

    private fun setupRecycler() {
        binding.rvNews.apply {
            layoutManager = linearLayoutManager
            adapter = headlinesAdapter
        }
    }

    private fun observeRecyclerViewScrolling() {
        val recyclerViewScroller = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = linearLayoutManager!!.childCount
                totalItems = linearLayoutManager!!.itemCount
                scrollOutItems = linearLayoutManager!!.findFirstVisibleItemPosition()
                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false
                    pageCount += 10
                    getLatestHeadlines()
                }
            }
        }
        binding.rvNews.addOnScrollListener(recyclerViewScroller)
    }


    private fun getLatestHeadlines() {
        viewModel.getLatestHeadlines(pageCount).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    mainViewModel.newsArticleResponse = it.data
                    it.data?.article?.let { articles ->
                        newsArticleList.clear()
                        newsArticleList.addAll(articles)
                    }
                    headlinesAdapter.submitList(newsArticleList)
                }
                Resource.Status.ERROR -> {

                }
                Resource.Status.LOADING -> {

                }
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.et_search -> {
                (activity as MainActivity).hideLocation()
                navController?.navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
    }

    override fun onNewsClick(article: NewsArticleResponse.Article) {
        (activity as MainActivity).hideWholeToolbar()
        mainViewModel.postDetailedArticle(article)
        navController?.navigate(R.id.action_homeFragment_to_detailsFragment)
    }


}