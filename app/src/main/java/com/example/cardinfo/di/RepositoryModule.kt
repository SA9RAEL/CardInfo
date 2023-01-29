package com.example.cardinfo.di

import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.data.repository.CardRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepositoryImpl(cardRepositoryImpl: CardRepositoryImpl): CardRepository
}