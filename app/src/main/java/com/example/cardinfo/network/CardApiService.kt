package com.example.cardinfo.network

import com.example.cardinfo.data.model.CardResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CardApiService {
    @GET("{cardNumber}")
    suspend fun getCardInfo(@Path("cardNumber") cardNumber: Int): CardResponse?
}