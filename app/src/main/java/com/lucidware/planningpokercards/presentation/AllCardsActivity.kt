package com.lucidware.planningpokercards.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import com.lucidware.planningpokercards.R
import com.lucidware.planningpokercards.domain.Deck
import com.lucidware.planningpokercards.domain.DeckHolder
import com.lucidware.planningpokercards.presentation.ShowCardActivity.Companion.FIRST_POSITION
import kotlinx.android.synthetic.main.activity_all_cards.*

/**
 * Created by Paweł Świętochowski.
 */
class AllCardsActivity : AppCompatActivity(), CardViewHolder.CardClickedListener {

    private val adapter = AllCardsAdapter()
    private lateinit var deckInUse: Deck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_cards)

        deckInUse = DeckHolder.DECK
        setupCardsList()
        setupDeckSelection()
    }

    private fun setupCardsList() {
        adapter.setOnClickListener(this)
        cardsList.adapter = adapter
        cardsList.layoutManager = GridLayoutManager(this, SPAN_COUNT).also {
            it.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val isLastElement = (position == adapter.itemCount - 1)
                    val isAloneInLastRow = (adapter.itemCount % SPAN_COUNT == 1)
                    return if (isLastElement && isAloneInLastRow) SPAN_COUNT else 1
                }
            }
        }
    }

    private fun setupDeckSelection() {
        val position = if (deckInUse == Deck.T_SHIRT_CARDS) 1 else 0
        tabLayout.getTabAt(position)!!.select()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) { }

            override fun onTabUnselected(tab: TabLayout.Tab) { }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val deck = if (tab.position == 1) Deck.T_SHIRT_CARDS else Deck.STANDARD_CARDS
                val prefs = getSharedPreferences(CARDS_PREFS_NAME, Context.MODE_PRIVATE)
                prefs.edit().putString(ACTIVE_DECK_KEY, deck.id).apply()
                DeckHolder.setDeck(deck)
                cardsList.adapter = adapter
            }
        })
    }

    override fun onCardClicked(cardAdapterPosition: Int) {
        val intent = Intent()
        intent.putExtra(ShowCardActivity.CARD_ADAPTER_POSITION_EXTRA, cardAdapterPosition)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        val deckChanged = (deckInUse != DeckHolder.DECK)
        if (deckChanged) {
            val intent = Intent()
            intent.putExtra(ShowCardActivity.CARD_ADAPTER_POSITION_EXTRA, FIRST_POSITION)
            setResult(Activity.RESULT_OK, intent)
        }

        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.out_anim)
    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayout.clearOnTabSelectedListeners()
    }
}

private const val SPAN_COUNT = 3
