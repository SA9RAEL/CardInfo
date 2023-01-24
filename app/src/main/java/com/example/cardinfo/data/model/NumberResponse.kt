package com.example.cardinfo.data.model

import com.example.cardinfo.model.room.entities.Number
import com.google.gson.annotations.SerializedName

data class NumberResponse(
    @SerializedName("length")
    val length: Int,
    @SerializedName("luhn")
    val luhn: Boolean
)

fun NumberResponse.map(): Number {
    return Number(
        length = length,
        luhn = luhn
    )
}