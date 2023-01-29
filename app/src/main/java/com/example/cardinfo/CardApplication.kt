package com.example.cardinfo

import android.app.Application
import com.example.cardinfo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CardApplication : Application(), HasAndroidInjector {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
            .inject(this@CardApplication)
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}