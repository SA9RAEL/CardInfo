package com.example.cardinfo.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cardinfo.model.room.dao.CardDao
import com.example.cardinfo.model.room.entities.Bank
import com.example.cardinfo.model.room.entities.Card
import com.example.cardinfo.model.room.entities.Country
import com.example.cardinfo.model.room.entities.Number

@Database(
    version = 1,
    exportSchema = false,
    entities = [Card::class,
        Bank::class,
        Country::class,
        Number::class]
)
abstract class CardDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

}