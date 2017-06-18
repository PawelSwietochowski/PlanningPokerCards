package com.lucidware.planningpokercards

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.lucidware.planningpokercards.common.bindView

/**
 * Created by Paweł Świętochowski.
 */
class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var listener: CardClickedListener? = null
    private val cardMiniatureImageView: ImageView by bindView(R.id.cardMiniatureImageView)

    fun bind(cardImageResId: Int) {
        cardMiniatureImageView.setImageResource(cardImageResId)
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
