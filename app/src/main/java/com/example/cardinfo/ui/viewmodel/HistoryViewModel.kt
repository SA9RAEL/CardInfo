package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.model.room.entities.Card
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
   private val repository: CardRepository
) : ViewModel() {

    val allCardsInfo: LiveData<List<Card>> = repository.allCardsInfo

}