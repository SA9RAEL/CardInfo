package com.example.cardinfo.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.cardinfo.model.room.CardDatabase
import com.example.cardinfo.network.CardApiService
import com.example.cardinfo.ui.viewmodel.HistoryViewModel
import com.example.cardinfo.ui.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://lookup.binlist.net/"

@Module
abstract class AllModules {

    companion object {
        @Provides
        @Reusable
        fun provideRetrofit(): CardApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardApiService::class.java)

        @Singleton
        @Provides
        fun provideDb(context: Context): CardDatabase =
            Room.databaseBuilder(context, CardDatabase::class.java, CardDatabase.DB_NAME)
                .build()
    }

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}