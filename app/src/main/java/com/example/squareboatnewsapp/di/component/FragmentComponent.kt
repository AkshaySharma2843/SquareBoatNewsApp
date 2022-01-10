package com.example.squareboatnewsapp.di.component

import com.example.squareboatnewsapp.di.FragmentScope
import com.example.squareboatnewsapp.di.module.FragmentModule
import com.example.squareboatnewsapp.ui.details.DetailsFragment
import com.example.squareboatnewsapp.ui.home.HomeFragment
import com.example.squareboatnewsapp.ui.search.SearchFragment
import dagger.Component

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(detailsFragment: DetailsFragment)
}