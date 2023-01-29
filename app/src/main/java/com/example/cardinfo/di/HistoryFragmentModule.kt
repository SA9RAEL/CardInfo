package com.example.cardinfo.di

import com.example.cardinfo.ui.HistoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HistoryFragmentModule {

    @ContributesAndroidInjector
    fun contributeHistoryFragment(): HistoryFragment
}