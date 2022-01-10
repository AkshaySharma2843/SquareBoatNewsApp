package com.example.squareboatnewsapp.ui.sheets

import com.example.squareboatnewsapp.data.model.Country

interface CountrySelectionListener {

    fun onCountrySelect(countryId : Country)
}