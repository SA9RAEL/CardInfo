package com.example.cardinfo.data.mapper

import com.example.cardinfo.data.model.CardResponse
import com.example.cardinfo.data.model.map
import com.example.cardinfo.model.room.entities.Card
import javax.inject.Inject

class CardMapper @Inject constructor()  : (CardResponse) -> Card {
    override fun invoke(cardResponse: CardResponse): Card {
        return with(cardResponse) {
            Card(
                number = number?.map(),
                bank = bank?.map(),
                country = country?.map(),
                brand = brand,
                prepaid = prepaid,
                scheme = scheme,
                type = type
            )
        }
    }
}