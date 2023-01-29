package com.example.cardinfo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.cardinfo.databinding.FragmentHistoryBinding
import com.example.cardinfo.ui.adapter.CardListAdapter
import com.example.cardinfo.ui.viewmodel.HistoryViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HistoryFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HistoryViewModel> { viewModelFactory }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardAdapter = CardListAdapter()
        viewModel.allCardsInfo.observe(viewLifecycleOwner) { allInfo ->
            allInfo.let { cardAdapter.submitList(it) }
        }
        binding.recyclerView.adapter = cardAdapter

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}