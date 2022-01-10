package com.example.squareboatnewsapp.ui.search


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.squareboatnewsapp.databinding.FragmentSearchBinding
import com.example.squareboatnewsapp.di.component.FragmentComponent
import com.example.squareboatnewsapp.ui.base.BaseFragment
import com.example.squareboatnewsapp.ui.main.MainActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squareboatnewsapp.data.model.NewsArticleResponse
import com.example.squareboatnewsapp.ui.home.HomeHeadlinesAdapter
import com.example.squareboatnewsapp.ui.home.OnNewsClickListener
import com.example.squareboatnewsapp.ui.main.MainViewModel
import javax.inject.Inject


class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(FragmentSearchBinding::inflate), View.OnClickListener, OnNewsClickListener {

    private var navController : NavController? = null
    private var newsAdapter = HomeHeadlinesAdapter(this)

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun setupView() {
        navController = NavHostFragment.findNavController(this)
        (activity as MainActivity).binding.ivBack.setOnClickListener{
            navController?.popBackStack()
            (activity as MainActivity).hideSearch()
            closeKeyboard()
        }
        openKeyboard()
        addBackPressCallback()
        observeSearch()
        setupRecycler()
    }

    private fun setupRecycler() {
        binding.rvSearchNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun observeSearch() {
        binding.edSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                filterData(p0.toString())
            }

        })
        observeSearchResult()
    }

    private fun observeSearchResult() {
        mainViewModel.searchResultResponseLiveData.observe(viewLifecycleOwner, {
            newsAdapter.submitList(it)
        })
    }

    private fun filterData(filterKey: String) {
       mainViewModel.filterData(filterKey)
    }

    private fun openKeyboard() {
        binding.edSearch.requestFocus()
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun addBackPressCallback() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeKeyboard()
                navController?.popBackStack()
                (activity as MainActivity).hideSearch()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

   private fun closeKeyboard(){
       val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
       imm?.hideSoftInputFromWindow(view!!.windowToken, 0)
   }

    override fun onClick(p0: View?) {

    }

    override fun onNewsClick(article: NewsArticleResponse.Article) {

    }
}