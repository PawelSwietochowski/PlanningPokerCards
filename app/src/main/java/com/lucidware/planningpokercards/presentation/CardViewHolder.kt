package com.lucidware.planningpokercards.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.lucidware.planningpokercards.databinding.CardMiniatureBinding

/**
 * Created by Paweł Świętochowski.
 */
class CardViewHolder(
    private val binding: CardMiniatureBinding
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private var listener: CardClickedListener? = null

    fun bind(cardImageResId: Int) {
        binding.cardMiniatureImageView.setImageResource(cardImageResId)
        binding.cardMiniatureImageView.setOnClickListener(this@CardViewHolder)
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
