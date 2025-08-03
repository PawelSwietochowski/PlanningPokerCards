package com.lucidware.planningpokercards.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.google.android.material.tabs.TabLayout
import com.lucidware.planningpokercards.common.addSystemWindowInsetToPadding
import com.lucidware.planningpokercards.databinding.ActivityAllCardsBinding
import com.lucidware.planningpokercards.domain.Deck
import com.lucidware.planningpokercards.domain.DeckHolder
import com.lucidware.planningpokercards.presentation.ShowCardActivity.Companion.FIRST_POSITION

/**
 * Created by Paweł Świętochowski.
 */
class AllCardsActivity : AppCompatActivity(), CardViewHolder.CardClickedListener {

    private val adapter = AllCardsAdapter()
    private lateinit var binding: ActivityAllCardsBinding
    private lateinit var deckInUse: Deck
    private var pickedCardPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCardsBinding.inflate(layoutInflater)
        binding.root.addSystemWindowInsetToPadding()
        setContentView(binding.root)
        onActivityCreated()
    }

    private fun onActivityCreated() {
        deckInUse = DeckHolder.DECK
        setupCardsList()
        setupDeckSelection()
    }

    private fun setupCardsList() {
        adapter.setOnClickListener(this)
        binding.cardsList.adapter = adapter
        binding.cardsList.layoutManager = GridLayoutManager(this, SPAN_COUNT).also {
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

        binding.tabLayout.getTabAt(position)?.select()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                val deck = if (tab.position == 1) Deck.T_SHIRT_CARDS else Deck.STANDARD_CARDS
                val prefs = getSharedPreferences(CARDS_PREFS_NAME, MODE_PRIVATE)
                prefs.edit().putString(ACTIVE_DECK_KEY, deck.id).apply()
                DeckHolder.setDeck(deck)
                binding.cardsList.adapter = adapter
            }
        })
    }

    override fun onCardClicked(cardAdapterPosition: Int) {
        pickedCardPosition = cardAdapterPosition
        finish()
    }

    override fun finish() {
        val deckChanged = (deckInUse != DeckHolder.DECK)
        if (deckChanged || pickedCardPosition != null) {
            val intent = Intent()
            intent.putExtra(ShowCardActivity.CARD_ADAPTER_POSITION_EXTRA, pickedCardPosition ?: FIRST_POSITION)
            setResult(RESULT_OK, intent)
        }
        super.finish()
    }

    override fun onDestroy() {
        binding.tabLayout.clearOnTabSelectedListeners()
        super.onDestroy()
    }
}

private const val SPAN_COUNT = 3
