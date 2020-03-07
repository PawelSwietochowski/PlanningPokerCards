package com.lucidware.planningpokercards.domain

import com.lucidware.planningpokercards.R

/**
 * Created by Paweł Świętochowski.
 */
object DeckHolder {

    var CARDS = createStandardCards()
        private set

    var DECK = Deck.STANDARD_CARDS
        private set

    fun setDeck(deck: Deck) {
        DECK = deck
        CARDS = if (deck == Deck.T_SHIRT_CARDS) createTshirtCards() else createStandardCards()
    }
}

enum class Deck {
    STANDARD_CARDS,
    T_SHIRT_CARDS
}

fun createStandardCards() = arrayOf(
        Card(R.drawable.half, R.drawable.half_m, R.color.yellow, R.drawable.reverse),
        Card(R.drawable.one, R.drawable.one_m, R.color.peach, R.drawable.reverse),
        Card(R.drawable.two, R.drawable.two_m, R.color.red, R.drawable.reverse),
        Card(R.drawable.three, R.drawable.three_m, R.color.cyan, R.drawable.reverse),
        Card(R.drawable.five, R.drawable.five_m, R.color.violet, R.drawable.reverse),
        Card(R.drawable.eight, R.drawable.eight_m, R.color.magenta, R.drawable.reverse),
        Card(R.drawable.thirteen, R.drawable.thirteen_m, R.color.pink, R.drawable.reverse),
        Card(R.drawable.twenty, R.drawable.twenty_m, R.color.lime, R.drawable.reverse),
        Card(R.drawable.forty, R.drawable.forty_m, R.color.blue, R.drawable.reverse),
        Card(R.drawable.hundred, R.drawable.hundred_m, R.color.biscuit, R.drawable.reverse),
        Card(R.drawable.infinity, R.drawable.infinity_m, R.color.orange, R.drawable.reverse),
        Card(R.drawable.question, R.drawable.question_m, R.color.green, R.drawable.reverse)
)

fun createTshirtCards() = arrayOf(
        Card(R.drawable.xs, R.drawable.xs_m, R.color.lime, R.drawable.reverse2),
        Card(R.drawable.s, R.drawable.s_m, R.color.yellow, R.drawable.reverse2),
        Card(R.drawable.m, R.drawable.m_m, R.color.cyan, R.drawable.reverse2),
        Card(R.drawable.l, R.drawable.l_m, R.color.violet, R.drawable.reverse2),
        Card(R.drawable.xl, R.drawable.xl_m, R.color.magenta, R.drawable.reverse2),
        Card(R.drawable.xxl, R.drawable.xxl_m, R.color.orange, R.drawable.reverse2),
        Card(R.drawable.question2, R.drawable.question2_m, R.color.red, R.drawable.reverse2)
)