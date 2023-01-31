package com.example.cardinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class CommonViewModelFactory @Inject constructor(
    private val map: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map.getValue(modelClass as Class<out ViewModel>).get() as T
    }
}