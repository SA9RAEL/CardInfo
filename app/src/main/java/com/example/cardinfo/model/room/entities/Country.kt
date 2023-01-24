package com.example.cardinfo.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Country(
    @PrimaryKey(autoGenerate = true)
    val countryId: Int = 0,
    val numeric: String?,
    val alpha2: String?,
    val countryName: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)