package com.example.cardinfo.di

import com.example.cardinfo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModel {

    @ContributesAndroidInjector(
        modules = [
            SearchFragmentModule::class,
            HistoryFragmentModule::class
        ]
    )

    fun contributeMainActivity(): MainActivity

}