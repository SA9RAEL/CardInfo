package com.example.cardinfo

import android.app.Application
import com.example.cardinfo.di.AppComponent
import com.example.cardinfo.di.DaggerAppComponent

class CardApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().withContext(this).build()
    }
}