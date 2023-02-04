package com.example.cardinfo.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cardinfo.CardApplication
import com.example.cardinfo.R
import com.example.cardinfo.databinding.FragmentSearchBinding
import com.example.cardinfo.model.room.entities.Card
import com.example.cardinfo.ui.viewmodel.DelegateFragment
import com.example.cardinfo.ui.viewmodel.SearchViewModel

private const val MAX_LENGTH = 8

class SearchFragment : DelegateFragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun onAttach(context: Context) {
        (context.applicationContext as CardApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        configureView()
    }

    private fun bindInformation(card: Card) {
        binding.apply {
            schemeResultTextView.text = card.scheme
            typeResultTextView.text = card.type
            bankNameTextView.text = card.bank?.bankName
            bankCityTextView.text = card.bank?.city
            bankUrlTextView.text = card.bank?.url
            bankNumberTextView.text = card.bank?.phone
            brandResultTextView.text = card.brand
            countryNameTextView.text = card.country?.countryName
            prepaidResultTextView.text = card.prepaid.toString()
            cardNumberLuhnResultTextView.text = card.number?.luhn.toString()
            cardNumberLengthResultTextView.text = card.number?.length.toString()
            countryLatitudeResultTextView.text = card.country?.latitude.toString()
            countryLongitudeResultTextView.text = card.country?.longitude.toString()
        }
    }

    private fun observeViewModel() {
        viewModel.cardInfo.observe(viewLifecycleOwner) { oneCardInfo ->
            bindInformation(oneCardInfo)
        }

        viewModel.failure.observe(viewLifecycleOwner) { failure ->
            Toast.makeText(requireContext(), failure, Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureView() {
        binding.apply {
            val textWatcher: TextWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val editText = editText.text
                    searchButton.isEnabled = editText.length == MAX_LENGTH
                }

                override fun afterTextChanged(s: Editable?) = Unit

            }

            editText.addTextChangedListener(textWatcher)

            searchButton.setOnClickListener {
                val cardNumber = binding.editText.text.toString().toInt()
                viewModel.getCardInfo(cardNumber)
            }

            fab.setOnClickListener {
                findNavController().navigate(
                    R.id.action_searchFragment_to_historyFragment
                )
            }
            countryNameTextView.setOnClickListener {
                onCountryClick()
            }

        }
    }

    private fun onCountryClick() {
        val latitude = binding.countryLatitudeResultTextView.text
        val longitude = binding.countryLongitudeResultTextView.text
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        startActivity(mapIntent)
    }

}