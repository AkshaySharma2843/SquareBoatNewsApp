package com.example.squareboatnewsapp.ui.sheets

import com.example.squareboatnewsapp.data.model.Country
import com.example.squareboatnewsapp.databinding.ItemCountryBinding
import com.example.squareboatnewsapp.ui.base.BaseAdapter

class CountryViewHolder(private val binding: ItemCountryBinding) : BaseAdapter.BaseViewHolder(binding) {

    fun bindCountries(country: Country, listener: CountrySelectionListener) {
        binding.tvCountry.text = country.countryName
        binding.clCountry.setOnClickListener {
            country.let { country -> listener.onCountrySelect(country) }
        }
        binding.radioCountry.setOnClickListener {
            country.let { country -> listener.onCountrySelect(country) }
        }
    }
}