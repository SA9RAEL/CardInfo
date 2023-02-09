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
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://binlist.net/"

@Module
abstract class AllModules {

    companion object {

        @Provides
        @Reusable
        fun provideCertificate(): CertificatePinner = CertificatePinner.Builder()
            .add(
                "binlist.net",
                "sha256/AiHxck5pzQY4+H/dRaViB9zMOTKfbdWp0GPSN96twwc="
            )
            .add(
                "binlist.net",
                "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4="
            )
            .add(
                "binlist.net",
                "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o="
            )
            .build()

        @Provides
        @Reusable
        fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Provides
        @Reusable
        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .certificatePinner(
                    provideCertificate()
                )
                .addInterceptor(
                    httpLoggingInterceptor()
                )
                .build()
        }

        @Provides
        @Reusable
        fun provideRetrofit(): CardApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                provideHttpClient()
            )
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
    @[IntoMap ViewModelKey(HistoryViewModel::class)]
    abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(SearchViewModel::class)]
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}