package com.example.cardinfo.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bank(
    @PrimaryKey(autoGenerate = true)
    val bankId: Int = 0,
    val bankName: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)