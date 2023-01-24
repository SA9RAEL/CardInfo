package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.*
import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.model.room.entities.Card
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: CardRepository) : ViewModel() {

    private val _cardInfo = MutableLiveData<Card>()
    val cardInfo: LiveData<Card> = _cardInfo

    private val _failure = MutableLiveData<String>()
    val failure: LiveData<String> = _failure

    fun getCardInfo(cardNumber: Int) {
        viewModelScope.launch {
            repository.getCardInfo(cardNumber).fold(
                onSuccess = { _cardInfo.value = it },
                onFailure = { _failure.value = it.message }
            )
        }
    }
}

class SearchViewModelFactory(private val repository: CardRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED CAST")
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}