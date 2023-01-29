package com.example.cardinfo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardinfo.ui.viewmodel.HistoryViewModel
import com.example.cardinfo.ui.viewmodel.SearchViewModel
import com.example.cardinfo.ui.viewmodel.ViewModelFactory
import com.example.cardinfo.ui.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun searchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun historyViewModel(historyViewModel: HistoryViewModel): ViewModel

}