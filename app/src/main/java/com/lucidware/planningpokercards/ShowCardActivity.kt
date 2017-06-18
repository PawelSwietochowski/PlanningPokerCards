package com.lucidware.planningpokercards

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.lucidware.planningpokercards.common.ExtendedViewPager
import com.lucidware.planningpokercards.common.bindView

/**
 * Created by Paweł Świętochowski.
 */
class ShowCardActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, CardView.CardSwipedListener {

    private val pokerCards = arrayOf(
            Card(R.drawable.half, R.color.cardHalf),
            Card(R.drawable.one, R.color.card1),
            Card(R.drawable.two, R.color.card2),
            Card(R.drawable.three, R.color.card3),
            Card(R.drawable.five, R.color.card5),
            Card(R.drawable.eight, R.color.card8),
            Card(R.drawable.thirteen, R.color.card13),
            Card(R.drawable.twenty, R.color.card20),
            Card(R.drawable.forty, R.color.card40),
            Card(R.drawable.hundred, R.color.card100),
            Card(R.drawable.infinity, R.color.cardInfinity),
            Card(R.drawable.question, R.color.cardQuestion)
    )

    private val floatingActionButton: FloatingActionButton by bindView(R.id.fab)
    private val viewPager: ExtendedViewPager by bindView(R.id.viewPager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_card)

        val cardsAdapter = CardsAdapter(pokerCards)
        cardsAdapter.setOnCardSwipedListener(this)
        viewPager.adapter = cardsAdapter
        viewPager.addOnPageChangeListener(this)
        floatingActionButton.setOnClickListener { startAllCardsActivity() }
        adjustFabColor(pokerCards[FIRST_POSITION])
    }

    private fun startAllCardsActivity() {
        val intent = Intent(this, AllCardsActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
        overridePendingTransition(R.anim.in_anim, 0)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        adjustFabColor(pokerCards[position])
    }

    override fun onCardSwipeStarted() {
        viewPager.canScroll = false
    }

    override fun onCardSwiped(card: Card) {
        pokerCards.forEach { it.showingReverse = card.showingReverse }
        adjustFabColor(card)
        viewPager.adapter.notifyDataSetChanged()
        viewPager.canScroll = true
    }

    private fun adjustFabColor(card: Card) {
        val color = ContextCompat.getColor(this, getColorRes(card))
        floatingActionButton.backgroundTintList = ColorStateList.valueOf(color)
    }

    private fun getColorRes(card: Card): Int {
        return if (card.showingReverse) R.color.cardReverse else card.cardColorResId
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val cardAdapterPosition = data.getIntExtra(CARD_ADAPTER_POSITION_EXTRA, INVALID_POSITION)
            if (cardAdapterPosition != INVALID_POSITION) {
                viewPager.smoothScrollToPosition(cardAdapterPosition, PAGE_SWITCH_DELAY)
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 1234
        private const val PAGE_SWITCH_DELAY = 100L
        private const val INVALID_POSITION = -1
        private const val FIRST_POSITION = 0
        const val CARD_ADAPTER_POSITION_EXTRA = "cardAdapterPosition"
    }
}
