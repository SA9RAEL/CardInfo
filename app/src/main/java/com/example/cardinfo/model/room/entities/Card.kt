package com.example.cardinfo.model.room.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Cardable entity to be stored in the cardable_databaase.
 */
@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded
    val number: Number?,
    @Embedded
    val country: Country?,
    @Embedded
    val bank: Bank?
)