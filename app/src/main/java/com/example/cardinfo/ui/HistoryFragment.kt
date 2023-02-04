package com.example.cardinfo.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cardinfo.CardApplication
import com.example.cardinfo.R
import com.example.cardinfo.databinding.FragmentHistoryBinding
import com.example.cardinfo.ui.adapter.CardListAdapter
import com.example.cardinfo.ui.viewmodel.DelegateFragment
import com.example.cardinfo.ui.viewmodel.HistoryViewModel

class HistoryFragment : DelegateFragment(R.layout.fragment_history) {

    private val viewModel: HistoryViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding(FragmentHistoryBinding::bind)

    override fun onAttach(context: Context) {
        (context.applicationContext as CardApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardAdapter = CardListAdapter()

        viewModel.allCardsInfo.observe(viewLifecycleOwner) { allInfo ->
            allInfo.let { cardAdapter.submitList(it) }
        }
        binding.recyclerView.adapter = cardAdapter
    }
}