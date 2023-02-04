package com.example.cardinfo.ui.viewmodel

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class DelegateFragment(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

}