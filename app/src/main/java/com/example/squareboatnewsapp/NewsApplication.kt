package com.example.squareboatnewsapp

import android.app.Application
import com.example.squareboatnewsapp.di.component.ApplicationComponent
import com.example.squareboatnewsapp.di.component.DaggerApplicationComponent
import com.example.squareboatnewsapp.di.module.ApplicationModule
import timber.log.Timber

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
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