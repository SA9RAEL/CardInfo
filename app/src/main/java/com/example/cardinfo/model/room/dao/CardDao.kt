package com.example.cardinfo.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cardinfo.model.room.entities.Card

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table")
    fun getAllCardsInfo(): LiveData<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)

}