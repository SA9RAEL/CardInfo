package com.example.cardinfo.di

import com.example.cardinfo.network.CardApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * 1. Предоставление интерфейсам реализации, с помощью аннотации @Binds.
 * 2. Конфигурация объектов, реализуется с промощью аннотации @Provides.
 *
 *    Что такое DoubleLockingCheck Pattern?
 *    Как реализовать Singleton?
 */

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): CardApiService {
        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardApiService::class.java)
    }

}