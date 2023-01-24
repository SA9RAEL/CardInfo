package com.example.cardinfo

import android.app.Application
import android.content.Context
import com.example.cardinfo.data.mapper.CardMapper
import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.model.room.CardDatabase

class CardApplication : Application() {

    private val database by lazy { CardDatabase.getDatabase(this) }
    val repository by lazy { CardRepository(database.cardDao(), CardMapper()) }

    companion object {
        fun getRepository(context: Context): CardRepository {
            return (context.applicationContext as CardApplication).repository
        }
    }

}