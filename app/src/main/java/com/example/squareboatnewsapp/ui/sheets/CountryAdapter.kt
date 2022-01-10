package com.example.squareboatnewsapp.ui.sheets

import android.view.ViewGroup
import com.example.squareboatnewsapp.data.model.Country
import com.example.squareboatnewsapp.databinding.ItemCountryBinding
import com.example.squareboatnewsapp.ui.base.BaseAdapter

class CountryAdapter(
    private val listOfCountries: ArrayList<Country>,
    private val listener: CountrySelectionListener
) : BaseAdapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(parent.inflateBinding(ItemCountryBinding::inflate))

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = listOfCountries[position]
        holder.bindCountries(country, listener)
    }

    override fun getItemCount(): Int = listOfCountries.size
}