package com.example.squareboatnewsapp.ui.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squareboatnewsapp.data.model.Country
import com.example.squareboatnewsapp.databinding.BottomSheetCountryBinding
import com.example.squareboatnewsapp.utils.constants.BundleConstants.COUNTRY_BUNDLE_KEY
import com.example.squareboatnewsapp.utils.constants.UrlConstants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.ArrayList

class CountryBottomSheet : BottomSheetDialogFragment(), View.OnClickListener, CountrySelectionListener {

    private lateinit var binding : BottomSheetCountryBinding
    private var countryList = ArrayList<Country>()

    companion object {
        fun createNewInstance(bundle : Bundle) : BottomSheetDialogFragment{
            val countrySheet = CountryBottomSheet()
            countrySheet.arguments = bundle
            return countrySheet
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryList = it.getParcelableArrayList(COUNTRY_BUNDLE_KEY)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCountryBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.rvCountry.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = CountryAdapter(countryList, this@CountryBottomSheet)
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onCountrySelect(country: Country) {
        UrlConstants.countryCodeToCall = country.countryCode ?: "in"
        UrlConstants.countryName = country.countryName ?: "India"
        UrlConstants.toRefreshData.postValue(true)
        dismissAllowingStateLoss()
    }

}