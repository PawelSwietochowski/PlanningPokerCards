package com.lucidware.planningpokercards.domain

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * Created by Paweł Świętochowski.
 */
data class Card(
    @DrawableRes val imageResId: Int,
    @DrawableRes val miniatureResId: Int,
    @ColorRes val colorResId: Int,
    @DrawableRes val reverseResId: Int
) {
    var showingReverse = false
}