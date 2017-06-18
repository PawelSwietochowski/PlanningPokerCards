package com.lucidware.planningpokercards.common

import android.app.Activity
import android.content.Context
import android.hardware.SensorManager
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import com.lucidware.planningpokercards.CardView
import com.squareup.seismic.ShakeDetector

/**
 * Created by Paweł Świętochowski.
 */
class ExtendedViewPager : ViewPager, ShakeDetector.Listener {

    var canScroll = true

    constructor(context: Context): super(context) {
        initializeShakeDetector()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initializeShakeDetector()
    }

    private fun initializeShakeDetector() {
        if (!isInEditMode) {
            val sensorManager = context.getSystemService(Activity.SENSOR_SERVICE) as SensorManager
            val shakeDetector = ShakeDetector(this)
            shakeDetector.setSensitivity(ShakeDetector.SENSITIVITY_LIGHT)
            shakeDetector.start(sensorManager)
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return canScroll && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return canScroll && super.onInterceptTouchEvent(ev)
    }

    override fun hearShake() {
        val cardView = findViewWithTag(currentItem) as CardView?
        cardView?.swipeCard()
    }

    fun smoothScrollToPosition(cardAdapterPosition: Int, pageSwitchDelay: Long) {
        postDelayed({ currentItem = cardAdapterPosition }, pageSwitchDelay)
    }
}