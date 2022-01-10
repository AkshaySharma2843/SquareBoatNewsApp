package com.example.squareboatnewsapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.squareboatnewsapp.databinding.FragmentDetailsBinding
import com.example.squareboatnewsapp.databinding.FragmentHomeBinding
import com.example.squareboatnewsapp.di.component.FragmentComponent
import com.example.squareboatnewsapp.ui.base.BaseFragment
import com.example.squareboatnewsapp.ui.home.HomeViewModel

class DetailsFragment  : BaseFragment<HomeViewModel, FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    override fun setupView() {}

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

}