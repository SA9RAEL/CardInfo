package com.example.cardinfo.data.model

import com.example.cardinfo.model.room.entities.Country
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class CountryResponse(
    @SerializedName("numeric")
    val numeric: String,
    @SerializedName("alpha2")
    val alpha2: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("emoji")
    val emoji: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)

fun CountryResponse.map(): Country {
    return Country(
        numeric = this.numeric,
        alpha2 = this.alpha2,
        countryName = this.name,
        emoji = this.emoji,
        currency = this.currency,
        latitude = this.latitude,
        longitude = this.longitude
    )
}