package com.example.squareboatnewsapp.ui.details


import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.squareboatnewsapp.databinding.FragmentDetailsBinding
import com.example.squareboatnewsapp.di.component.FragmentComponent
import com.example.squareboatnewsapp.ui.base.BaseFragment
import com.example.squareboatnewsapp.ui.home.HomeViewModel
import com.example.squareboatnewsapp.ui.main.MainActivity
import com.example.squareboatnewsapp.ui.main.MainViewModel
import javax.inject.Inject

class DetailsFragment  : BaseFragment<DetailsViewModel, FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private var navController : NavController?= null


    override fun setupView() {
        (activity as MainActivity).hideOthersShowBack()
        navController = NavHostFragment.findNavController(this)
        observeDetailedArticle()
        addBackPressCallback()

    }

    private fun addBackPressCallback() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                navController?.popBackStack()
                (activity as MainActivity).hideSearch()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun observeDetailedArticle() {
        mainViewModel.articleLiveData.observe(viewLifecycleOwner, {
            if(it != null){
                Glide.with(requireActivity())
                    .load(it.urlToImage)
                    .into(binding.ivImageNews)
            }
            binding.tvTitle.text = it.source.name
            binding.tvNewsDetails.text = it.description
            binding.tvPreview.text = it.title
        })
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

}