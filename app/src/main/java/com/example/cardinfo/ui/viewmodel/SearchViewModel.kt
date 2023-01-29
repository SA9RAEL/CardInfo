package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.model.room.entities.Card
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: CardRepository) : ViewModel() {

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