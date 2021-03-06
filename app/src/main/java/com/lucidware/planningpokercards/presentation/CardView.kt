package com.lucidware.planningpokercards.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import com.lucidware.planningpokercards.R
import com.lucidware.planningpokercards.common.animations.FlipAnimator
import com.lucidware.planningpokercards.common.animations.FlipDirection
import com.lucidware.planningpokercards.domain.Card
import kotlinx.android.synthetic.main.view_card.view.*

/**
 * Created by Paweł Świętochowski.
 */
class CardView : FlipAnimator {

    override val flipDirection = FlipDirection.RIGHT_TO_LEFT
    override val animationDuration = 400L
    override val interpolator = AccelerateDecelerateInterpolator()

    private lateinit var card: Card
    private var listener: CardSwipedListener? = null

    constructor(context: Context) : super(context) {
        initialize(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(context)
    }

    private fun initialize(context: Context) {
        inflate(context, R.layout.view_card, this)
        cardImageView.setOnClickListener { swipeCard() }
        cardReverseView.setOnClickListener { swipeCard() }
    }

    fun setCard(card: Card) {
        this.card = card
        cardImageView.setImageResource(card.imageResId)
        cardReverseView.setImageResource(card.reverseResId)
        adjustViewToData()
    }

    fun adjustViewToData() {
        displayedChild = if (card.showingReverse) CARD_REVERSE else CARD_IMAGE
    }

    fun setOnCardSwipedListener(listener: CardSwipedListener?) {
        this.listener = listener
    }

    fun swipeCard() {
        if (!isFlipping) {
            doFlipTransition()
        }
    }

    override fun onAnimationEnd(animation: Animation) {
        super.onAnimationEnd(animation)
        card.showingReverse = !card.showingReverse
        listener?.onCardSwiped(card)
    }

    override fun onAnimationStart(animation: Animation) {
        super.onAnimationStart(animation)
        listener?.onCardSwipeStarted()
    }

    companion object {
        private const val CARD_IMAGE = 0
        private const val CARD_REVERSE = 1
    }

    interface CardSwipedListener {
        fun onCardSwiped(card: Card)
        fun onCardSwipeStarted()
    }
}