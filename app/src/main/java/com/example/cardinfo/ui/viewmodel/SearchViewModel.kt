package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _data = MutableLiveData<Resource<Card>>()
    val data: LiveData<Resource<Card>> = _data

    val error = SingleLiveEvent<Resource<Card>>()

    private val _isLoading = MutableLiveData<Resource<Card>>()
    val isLoading: LiveData<Resource<Card>> = _isLoading

    fun getCardInfo(cardNumber: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(Resource.Loading())
            repository.getCardInfo(cardNumber).fold(
                onSuccess = { data -> _data.postValue((Resource.Success(data))) },
                onFailure = { error ->
                    when (error) {
                        is SSLHandshakeException ->
                            this@SearchViewModel.error.postValue((Resource.Error("SSLHandshakeException")))

                        is HttpException ->
                            this@SearchViewModel.error.postValue((Resource.Error("HttpException")))

                        else ->
                            this@SearchViewModel.error.postValue((Resource.Error("Invalid card number")))
                    }
                }
            )
        }

}