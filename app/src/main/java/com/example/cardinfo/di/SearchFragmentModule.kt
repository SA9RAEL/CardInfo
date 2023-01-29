package com.example.cardinfo.di

import com.example.cardinfo.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SearchFragmentModule {

    @ContributesAndroidInjector
    fun contributeSearchFragment(): SearchFragment
}