package com.example.cardinfo.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Number(
    @PrimaryKey(autoGenerate = true)
    val numberId: Int = 0,
    val length: Int?,
    val luhn: Boolean?
)


