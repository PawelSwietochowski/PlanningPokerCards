package com.lucidware.planningpokercards.presentation

import android.app.Application
import android.content.Context
import com.lucidware.planningpokercards.domain.Deck
import com.lucidware.planningpokercards.domain.DeckHolder

/**
 * Created by Paweł Świętochowski.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        val prefs = getSharedPreferences(CARDS_PREFS_NAME, Context.MODE_PRIVATE)
        val deckInUse = prefs.getString(ACTIVE_DECK_KEY, "")

        DeckHolder.setDeck(if (deckInUse == Deck.T_SHIRT_CARDS.name) Deck.T_SHIRT_CARDS else Deck.STANDARD_CARDS)
    }
}

const val CARDS_PREFS_NAME = "card_prefs"
const val ACTIVE_DECK_KEY = "active_deck"