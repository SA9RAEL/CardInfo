package com.example.cardinfo.data.model

import com.example.cardinfo.model.room.entities.Bank
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class BankResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("city")
    val city: String
)

fun BankResponse.map(): Bank {
    return Bank(
        bankName = this.name,
        url = this.url,
        phone = this.phone,
        city = this.city
    )
}