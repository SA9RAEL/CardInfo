package com.example.cardinfo.network

import com.example.cardinfo.data.model.CardResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://lookup.binlist.net/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface CardApiService {
    @GET("{cardNumber}")
    suspend fun getCardInfo(@Path("cardNumber") cardNumber: Int): CardResponse?
}


val retrofitService: CardApiService by lazy {
    retrofit.create(CardApiService::class.java)
}