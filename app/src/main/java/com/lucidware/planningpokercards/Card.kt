package com.lucidware.planningpokercards

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes

/**
 * Created by Paweł Świętochowski.
 */
data class Card(@DrawableRes val cardImageResId : Int, @ColorRes val cardColorResId : Int) {
    var showingReverse = false
}