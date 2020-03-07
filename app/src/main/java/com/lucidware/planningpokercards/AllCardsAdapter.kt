package com.lucidware.planningpokercards

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Paweł Świętochowski.
 */
class AllCardsAdapter : RecyclerView.Adapter<CardViewHolder>(), CardViewHolder.CardClickedListener {

    private var listener: CardViewHolder.CardClickedListener? = null

    private val cards: IntArray = intArrayOf(
            R.drawable.half_m,
            R.drawable.one_m,
            R.drawable.two_m,
            R.drawable.three_m,
            R.drawable.five_m,
            R.drawable.eight_m,
            R.drawable.thirteen_m,
            R.drawable.twenty_m,
            R.drawable.forty_m,
            R.drawable.hundred_m,
            R.drawable.infinity_m,
            R.drawable.question_m)

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
        holder.bind(cards[position])
    }

    override fun getItemCount() = cards.size

    override fun onCardClicked(cardAdapterPosition: Int) {
        listener?.onCardClicked(cardAdapterPosition)
    }
}



