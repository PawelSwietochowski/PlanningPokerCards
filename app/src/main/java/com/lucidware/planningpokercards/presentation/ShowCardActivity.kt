package com.lucidware.planningpokercards.presentation

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.lucidware.planningpokercards.R
import com.lucidware.planningpokercards.domain.Card
import com.lucidware.planningpokercards.domain.DeckHolder.CARDS
import kotlinx.android.synthetic.main.activity_show_card.*

/**
 * Created by Paweł Świętochowski.
 */
class ShowCardActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, CardView.CardSwipedListener {

    private val cardsAdapter = CardsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_card)

        cardsAdapter.setOnCardSwipedListener(this)
        viewPager.adapter = cardsAdapter
        viewPager.addOnPageChangeListener(this)
        floatingActionButton.setOnClickListener { startAllCardsActivity() }
        adjustFabColor(CARDS[FIRST_POSITION])
    }

    private fun startAllCardsActivity() {
        val intent = Intent(this, AllCardsActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
        overridePendingTransition(R.anim.in_anim, 0)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        adjustFabColor(CARDS[position])
    }

    override fun onCardSwipeStarted() {
        viewPager.canScroll = false
    }

    override fun onCardSwiped(card: Card) {
        CARDS.forEach { it.showingReverse = card.showingReverse }
        adjustFabColor(card)
        cardsAdapter.notifyDataSetChanged()
        viewPager.canScroll = true
    }

    private fun adjustFabColor(card: Card) {
        val color = ContextCompat.getColor(this, getColorRes(card))
        floatingActionButton.backgroundTintList = ColorStateList.valueOf(color)
    }

    private fun getColorRes(card: Card): Int {
        return if (card.showingReverse) R.color.gray else card.colorResId
    }

    override fun onResume() {
        super.onResume()
        viewPager.startShakeDetector()
    }

    override fun onPause() {
        super.onPause()
        viewPager.stopShakeDetector()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            viewPager.adapter = cardsAdapter
            val cardAdapterPosition = data.getIntExtra(CARD_ADAPTER_POSITION_EXTRA, FIRST_POSITION)
            viewPager.smoothScrollToPosition(cardAdapterPosition, PAGE_SWITCH_DELAY)
            adjustFabColor(CARDS[cardAdapterPosition])
        }
    }

    companion object {
        private const val REQUEST_CODE = 1234
        private const val PAGE_SWITCH_DELAY = 100L
        const val FIRST_POSITION = 0
        const val CARD_ADAPTER_POSITION_EXTRA = "cardAdapterPosition"
    }
}
