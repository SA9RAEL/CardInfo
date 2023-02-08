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
import javax.net.ssl.SSLHandshakeException

class SearchViewModel @Inject constructor(
    private val repository: CardRepository,
) : ViewModel() {

    private val _cardInfo = MutableLiveData<Card>()
    val cardInfo: LiveData<Card> = _cardInfo

    private val _failure = MutableLiveData<String>()
    val failure: LiveData<String> = _failure

    private val _state = MutableLiveData<Resource<Card>>()
    val state: LiveData<Resource<Card>> = _state

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    private var response: Response<CardResponse>? = null

    fun getCardInfo(cardNumber: Int) =
        viewModelScope.launch {
            _state.postValue(Resource.Loading())
            repository.getCardInfo(cardNumber).fold(
                onSuccess = { data -> _state.postValue((Resource.Success(data))) },
                onFailure = { error ->
                    when (error) {
                        is SSLHandshakeException -> _state.postValue((Resource.Error("SSLHandshakeException")))
                        else -> _state.postValue((Resource.Error("Another error")))
                    }
                }
            )
        }

}