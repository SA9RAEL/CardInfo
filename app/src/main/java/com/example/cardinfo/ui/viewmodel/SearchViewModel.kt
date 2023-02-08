package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardinfo.data.model.CardResponse
import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.model.room.entities.Card
import com.example.cardinfo.network.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: CardRepository,
) : ViewModel() {

    private val _cardInfo = MutableLiveData<Card>()
    val cardInfo: LiveData<Card> = _cardInfo

    private val _failure = MutableLiveData<String>()
    val failure: LiveData<String> = _failure

    private val _state = MutableLiveData<Resource<CardResponse>>()
    val state: LiveData<Resource<CardResponse>> = _state

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var response: Response<CardResponse>? = null

    fun getCardInfo(cardNumber: Int) =
        viewModelScope.launch {
            _state.postValue(Resource.Loading())
            if (response?.isSuccessful == true) {
                response?.body()?.let {
                    _state.postValue((Resource.Success(it)))
                }
                repository.getCardInfo(cardNumber).fold(
                    onSuccess = { _cardInfo.value = it },
                    onFailure = { _failure.value = it.message }
                )
            } else {
                _state.postValue(response?.message()?.let { Resource.Error(it) })
            }

        }

}