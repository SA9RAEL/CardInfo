package com.example.cardinfo.data.repository

import com.example.cardinfo.model.room.entities.Card

interface CardRepository {

    suspend fun getCardInfo(cardNumber: Int): Result<Card>
}