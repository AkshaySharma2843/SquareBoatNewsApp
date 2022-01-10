package com.example.squareboatnewsapp

import android.app.Application
import com.example.squareboatnewsapp.di.component.ApplicationComponent
import com.example.squareboatnewsapp.di.component.DaggerApplicationComponent
import com.example.squareboatnewsapp.di.module.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}