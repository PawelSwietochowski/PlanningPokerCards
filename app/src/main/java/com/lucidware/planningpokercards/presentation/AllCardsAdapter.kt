package com.lucidware.planningpokercards.presentation

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lucidware.planningpokercards.R
import com.lucidware.planningpokercards.domain.DeckHolder.CARDS

/**
 * Created by Paweł Świętochowski.
 */
class AllCardsAdapter : RecyclerView.Adapter<CardViewHolder>(), CardViewHolder.CardClickedListener {

    private var listener: CardViewHolder.CardClickedListener? = null

    fun setOnClickListener(listener: CardViewHolder.CardClickedListener) {
        this.listener = listener
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.card_miniature, null)
        val holder = CardViewHolder(layoutView)
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



