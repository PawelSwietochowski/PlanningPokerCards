package com.lucidware.planningpokercards.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucidware.planningpokercards.databinding.CardMiniatureBinding
import com.lucidware.planningpokercards.domain.DeckHolder.CARDS

/**
 * Created by Paweł Świętochowski.
 */
class AllCardsAdapter : RecyclerView.Adapter<CardViewHolder>(), CardViewHolder.CardClickedListener {

    private var listener: CardViewHolder.CardClickedListener? = null

    fun setOnClickListener(listener: CardViewHolder.CardClickedListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CardMiniatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = CardViewHolder(binding)
        holder.setOnClickListener(this)
        return holder
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(CARDS[position].miniatureResId)
    }

    override fun getItemCount() = CARDS.size

    override fun onCardClicked(cardAdapterPosition: Int) {
        listener?.onCardClicked(cardAdapterPosition)
    }
}