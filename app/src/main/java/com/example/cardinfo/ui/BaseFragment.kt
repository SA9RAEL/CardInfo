package com.example.cardinfo.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.cardinfo.ui.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

}