package com.lucidware.planningpokercards

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.card_miniature.view.*

/**
 * Created by Paweł Świętochowski.
 */
class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var listener: CardClickedListener? = null

    fun bind(cardImageResId: Int) {
        itemView.cardMiniatureImageView.setImageResource(cardImageResId)
        itemView.setOnClickListener(this)
    }

    fun setOnClickListener(listener: CardClickedListener) {
        this.listener = listener
    }

    override fun onClick(v: View?) {
        listener?.onCardClicked(adapterPosition)
    }

    interface CardClickedListener {
        fun onCardClicked(cardAdapterPosition: Int)
    }
}
