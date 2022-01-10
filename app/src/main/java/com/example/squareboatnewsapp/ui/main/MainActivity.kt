package com.example.squareboatnewsapp.ui.main

import android.os.Bundle
import com.example.squareboatnewsapp.databinding.ActivityMainBinding
import com.example.squareboatnewsapp.di.component.ActivityComponent
import com.example.squareboatnewsapp.ui.base.BaseActivity
import com.example.squareboatnewsapp.utils.common.Resource

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(bindingInflater = ActivityMainBinding:: inflate) {


    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {}
}