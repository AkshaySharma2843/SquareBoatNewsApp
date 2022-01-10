package com.example.squareboatnewsapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.squareboatnewsapp.databinding.FragmentSearchBinding
import com.example.squareboatnewsapp.di.component.FragmentComponent
import com.example.squareboatnewsapp.ui.base.BaseFragment

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun setupView() {
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)
}