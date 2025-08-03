package com.lucidware.planningpokercards.common

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.addSystemWindowInsetToPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.setPadding(
            paddingLeft + systemBarsInsets.left,
            paddingTop + systemBarsInsets.top,
            paddingRight + systemBarsInsets.right,
            paddingBottom + systemBarsInsets.bottom
        )
        insets
    }
}