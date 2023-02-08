package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardinfo.data.repository.CardRepository
import com.example.cardinfo.model.room.entities.Card
import com.example.cardinfo.network.Resource
import com.example.cardinfo.ui.singleliveevent.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class SearchViewModel @Inject constructor(
    private val repository: CardRepository,
) : ViewModel() {

    private val _state = SingleLiveEvent<Resource<Card>>()
    val state: LiveData<Resource<Card>> = _state

    fun getCardInfo(cardNumber: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(Resource.Loading())
            repository.getCardInfo(cardNumber).fold(
                onSuccess = { data -> _state.postValue((Resource.Success(data))) },
                onFailure = { error ->
                    when (error) {
                        is SSLHandshakeException ->
                            _state.postValue((Resource.Error("SSLHandshakeException")))

                        is HttpException ->
                            _state.postValue((Resource.Error("HttpException")))

                        else ->
                            _state.postValue((Resource.Error("Invalid card number")))
                    }
                }
            )
        }

}