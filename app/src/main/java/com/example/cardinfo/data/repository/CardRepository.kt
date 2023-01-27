package com.example.cardinfo.data.repository

import androidx.lifecycle.LiveData
import com.example.cardinfo.data.mapper.CardMapper
import com.example.cardinfo.model.room.dao.CardDao
import com.example.cardinfo.model.room.entities.Card
import com.example.cardinfo.network.CardApiService
import retrofit2.HttpException
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val cardDao: CardDao,
    private val cardMapper: CardMapper,
    private val api: CardApiService
) {

    val allCardsInfo: LiveData<List<Card>> by lazy { cardDao.getAllCardsInfo() }

    suspend fun getCardInfo(cardNumber: Int): Result<Card> {
        return try {
            val cardResponse = api.getCardInfo(cardNumber)
            if (cardResponse != null) {
                val card = cardMapper(cardResponse)
                cardDao.insert(card)
                Result.success(card)
            } else {
                Result.failure(Error())
            }
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}