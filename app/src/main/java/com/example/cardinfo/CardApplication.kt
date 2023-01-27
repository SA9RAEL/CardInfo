package com.example.cardinfo

import android.app.Application
import com.example.cardinfo.di.DaggerAppComponent
import com.example.cardinfo.model.room.CardDatabase

class CardApplication : Application() {

    private val database by lazy { CardDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().build()
    }


}