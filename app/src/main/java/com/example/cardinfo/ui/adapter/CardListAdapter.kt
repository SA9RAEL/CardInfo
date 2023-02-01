package com.example.cardinfo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cardinfo.databinding.RecyclerviewItemBinding
import com.example.cardinfo.model.room.entities.Card

/**
 * ListAdapter for the list of [Card]s retrieved from the database
 */
class CardListAdapter : ListAdapter<Card, CardListAdapter.CardViewHolder>(CARDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardViewHolder(
            RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CardViewHolder(
        private var binding: RecyclerviewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
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

    }

    companion object {
        private val CARDS_COMPARATOR = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem == newItem
            }
        }
    }

}