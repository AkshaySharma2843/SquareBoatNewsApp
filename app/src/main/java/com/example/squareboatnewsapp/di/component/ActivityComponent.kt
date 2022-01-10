package com.example.squareboatnewsapp.di.component

import com.example.squareboatnewsapp.di.ActivityScope
import com.example.squareboatnewsapp.di.module.ActivityModule
import com.example.squareboatnewsapp.ui.main.MainActivity
import dagger.Component

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity : MainActivity)
}