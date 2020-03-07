package com.lucidware.planningpokercards.presentation

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.lucidware.planningpokercards.domain.Card
import com.lucidware.planningpokercards.domain.DeckHolder.CARDS

/**
 * Created by Paweł Świętochowski.
 */
class CardsAdapter : PagerAdapter(), CardView.CardSwipedListener {

    private var listener: CardView.CardSwipedListener? = null

    fun setOnCardSwipedListener(listener: CardView.CardSwipedListener?) {
        this.listener = listener
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view = CardView(parent.context)
        view.tag = position
        view.setCard(CARDS[position])
        view.setOnCardSwipedListener(this)
        parent.addView(view)
        return view
    }

    override fun getItemPosition(obj: Any): Int {
        if (obj is CardView) {
            obj.adjustViewToData()
        }
        return super.getItemPosition(obj)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === (obj as View)
    }

    override fun getCount() = CARDS.size

    override fun destroyItem(parent: ViewGroup, position: Int, view: Any) {
        parent.removeView(view as View)
    }

    override fun onCardSwiped(card: Card) {
        listener?.onCardSwiped(card)
    }

    override fun onCardSwipeStarted() {
        listener?.onCardSwipeStarted()
    }
}