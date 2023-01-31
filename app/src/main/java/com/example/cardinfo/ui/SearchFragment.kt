package com.example.cardinfo.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cardinfo.CardApplication
import com.example.cardinfo.R
import com.example.cardinfo.databinding.FragmentSearchBinding
import com.example.cardinfo.model.room.entities.Card
import com.example.cardinfo.ui.viewmodel.CommonViewModelFactory
import com.example.cardinfo.ui.viewmodel.SearchViewModel
import javax.inject.Inject

private const val MAX_LENGTH = 8

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CommonViewModelFactory

    private val viewModel: SearchViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentSearchBinding? = null
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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
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
            prepaidResultTextView.text = card.prepaid.toString()
            cardNumberLengthResultTextView.text = card.number?.length.toString()
            cardNumberLuhnResultTextView.text = card.number?.luhn.toString()
            countryNameTextView.text = card.country?.countryName
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

            editText.setText("45717360")

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