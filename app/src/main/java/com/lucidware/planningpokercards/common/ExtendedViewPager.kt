package com.lucidware.planningpokercards.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.hardware.SensorManager
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import com.lucidware.planningpokercards.presentation.CardView
import com.squareup.seismic.ShakeDetector

/**
 * Created by Paweł Świętochowski.
 */
class ExtendedViewPager : ViewPager, ShakeDetector.Listener {

    var canScroll = true

    private val shakeDetector = ShakeDetector(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun startShakeDetector() {
        if (!isInEditMode) {
            val sensorManager = context.getSystemService(Activity.SENSOR_SERVICE) as SensorManager
            shakeDetector.setSensitivity(ShakeDetector.SENSITIVITY_LIGHT)
            shakeDetector.start(sensorManager)
        }
    }

    fun stopShakeDetector() {
        shakeDetector.stop()
    }

    @SuppressLint("ClickableViewAccessibility")
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