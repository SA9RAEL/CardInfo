package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cardinfo.data.repository.CardRepositoryImpl
import com.example.cardinfo.model.room.entities.Card
import javax.inject.Inject

class HistoryViewModel @Inject constructor(repository: CardRepositoryImpl) : ViewModel() {

    val allCardsInfo: LiveData<List<Card>> = repository.allCardsInfo

}