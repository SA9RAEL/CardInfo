package com.example.cardinfo.data.model

import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("number")
    val number: NumberResponse?,
    @SerializedName("country")
    val country: CountryResponse?,
    @SerializedName("bank")
    val bank: BankResponse?,
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("prepaid")
    val prepaid: Boolean?
)