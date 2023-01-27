package com.example.cardinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cardinfo.databinding.FragmentHistoryBinding
import com.example.cardinfo.ui.adapter.CardListAdapter

class HistoryFragment : Fragment() {

//    private val viewModel: HistoryViewModel by viewModels {
//        HistoryViewModelFactory(
//            (requireContext().applicationContext as CardApplication).repository
//        )
//    }
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

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


//        viewModel.allCardsInfo.observe(viewLifecycleOwner) { allInfo ->
//            allInfo.let { cardAdapter.submitList(it) }
//
//        }

        binding.recyclerView.adapter = cardAdapter


    }

}