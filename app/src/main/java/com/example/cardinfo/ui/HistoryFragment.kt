package com.example.cardinfo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cardinfo.CardApplication
import com.example.cardinfo.databinding.FragmentHistoryBinding
import com.example.cardinfo.ui.adapter.CardListAdapter
import com.example.cardinfo.ui.viewmodel.HistoryViewModel
import com.example.cardinfo.ui.viewmodel.ViewModelFactory
import javax.inject.Inject

class HistoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HistoryViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        (context.applicationContext as CardApplication).appComponent.inject(this)
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

}