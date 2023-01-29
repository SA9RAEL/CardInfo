package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.*
import com.example.cardinfo.data.repository.CardRepositoryImpl
import com.example.cardinfo.model.room.entities.Card
import javax.inject.Inject

class HistoryViewModel @Inject constructor(repository: CardRepositoryImpl) : ViewModel() {

    val allCardsInfo: LiveData<List<Card>> = repository.allCardsInfo

}

class HistoryViewModelFactory(private val repository: CardRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED CAST")
            return HistoryViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}