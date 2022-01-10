package com.example.squareboatnewsapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.squareboatnewsapp.R
import com.example.squareboatnewsapp.databinding.ActivityMainBinding
import com.example.squareboatnewsapp.di.component.ActivityComponent
import com.example.squareboatnewsapp.ui.base.BaseActivity
import com.example.squareboatnewsapp.ui.sheets.CountryBottomSheet
import com.example.squareboatnewsapp.utils.constants.BundleConstants
import com.example.squareboatnewsapp.utils.constants.UrlConstants

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(bindingInflater = ActivityMainBinding:: inflate), View.OnClickListener {
    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        binding.ivLocation.setOnClickListener(this)
        changeCountryName()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.iv_location -> {
                openBottomSheet()
            }
        }
    }

    private fun openBottomSheet() {
        val bundle = Bundle()
        val listOfCountries = viewModel.countryList
        bundle.putParcelableArrayList(BundleConstants.COUNTRY_BUNDLE_KEY, listOfCountries)
        val sheet = CountryBottomSheet.createNewInstance(bundle)
        sheet.show(supportFragmentManager, "COUNTRY_SHEET")
    }

    fun changeCountryName(){
        viewModel.toChangeCountry.observe(this, {
            binding.tvCountryName.text = UrlConstants.countryName
            viewModel.toChangeCountry.postValue(false)
        })
    }

    fun hideLocation() {
        binding.groupSearch.visibility = View.VISIBLE
        binding.groupMain.visibility = View.GONE
    }

    fun hideSearch() {
        binding.groupSearch.visibility = View.GONE
        binding.groupMain.visibility = View.VISIBLE
    }

    fun hideWholeToolbar() {
        binding.groupSearch.visibility = View.GONE
        binding.groupMain.visibility = View.GONE
    }

    fun hideOthersShowBack(){
        binding.groupSearch.visibility = View.GONE
        binding.groupMain.visibility = View.GONE
        binding.ivBack.visibility = View.VISIBLE
    }


}