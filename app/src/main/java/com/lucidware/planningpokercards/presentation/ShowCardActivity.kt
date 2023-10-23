package com.lucidware.planningpokercards.presentation

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.lucidware.planningpokercards.R
import com.lucidware.planningpokercards.databinding.ActivityShowCardBinding
import com.lucidware.planningpokercards.domain.Card
import com.lucidware.planningpokercards.domain.DeckHolder.CARDS

/**
 * Created by Paweł Świętochowski.
 */
class ShowCardActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, CardView.CardSwipedListener {

    private val cardsAdapter = CardsAdapter()
    private lateinit var binding: ActivityShowCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onActivityCreated()
    }

    private fun onActivityCreated() {
        cardsAdapter.setOnCardSwipedListener(this)
        binding.viewPager.adapter = cardsAdapter
        binding.viewPager.addOnPageChangeListener(this)
        binding.floatingActionButton.setOnClickListener { startAllCardsActivity() }
        adjustFabColor(CARDS[FIRST_POSITION])
    }

    private fun startAllCardsActivity() {
        val intent = Intent(this, AllCardsActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        adjustFabColor(CARDS[position])
    }

    override fun onCardSwipeStarted() {
        binding.viewPager.canScroll = false
    }

    override fun onCardSwiped(card: Card) {
        CARDS.forEach { it.showingReverse = card.showingReverse }
        adjustFabColor(card)
        cardsAdapter.notifyDataSetChanged()
        binding.viewPager.canScroll = true
    }

    private fun adjustFabColor(card: Card) {
        val color = ContextCompat.getColor(this, getColorRes(card))
        binding.floatingActionButton.backgroundTintList = ColorStateList.valueOf(color)
    }

    private fun getColorRes(card: Card): Int {
        return if (card.showingReverse) R.color.gray else card.colorResId
    }

    override fun onResume() {
        super.onResume()
        binding.viewPager.startShakeDetector()
    }

    override fun onPause() {
        super.onPause()
        binding.viewPager.stopShakeDetector()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            binding.viewPager.adapter = cardsAdapter
            val cardAdapterPosition = data.getIntExtra(CARD_ADAPTER_POSITION_EXTRA, FIRST_POSITION)
            binding.viewPager.smoothScrollToPosition(cardAdapterPosition, PAGE_SWITCH_DELAY)
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
