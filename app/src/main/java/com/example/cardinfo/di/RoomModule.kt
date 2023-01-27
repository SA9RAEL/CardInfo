package com.example.cardinfo.di

import android.content.Context
import androidx.room.Room
import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.model.room.CardDatabase
import com.example.cardinfo.model.room.dao.CardDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesDao(cardDatabase: CardDatabase): CardDao {
        return cardDatabase.cardDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): CardDatabase {
        return Room.databaseBuilder(context, CardDatabase::class.java, "card_database").build()
    }

    @Singleton
    @Provides
    fun provideRepository(cardRepository: CardRepository): CardRepository {
        return cardRepository
    }
}