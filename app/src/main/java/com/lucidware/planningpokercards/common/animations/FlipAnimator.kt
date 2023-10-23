package com.lucidware.planningpokercards.common.animations

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.widget.ViewAnimator

/**
 * Created by Paweł Świętochowski.
 */
abstract class FlipAnimator : ViewAnimator, Animation.AnimationListener {

    protected abstract val flipDirection: FlipDirection
    protected abstract val animationDuration: Long
    protected abstract val interpolator: Interpolator

    protected var isFlipping = false
        private set

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun doFlipTransition() {
        isFlipping = true
        val currentIndex = displayedChild
        val nextIndex = (currentIndex + 1) % childCount
        val flipDirection = if (nextIndex < currentIndex) flipDirection.reversed() else flipDirection

        val inOutAnimations = AnimationFactory.getFlipAnimation(
            currentView,
            flipDirection,
            animationDuration,
            interpolator,
            this
        )

        outAnimation = inOutAnimations[0]
        inAnimation = inOutAnimations[1]

        showNext()
    }

    override fun onAnimationEnd(animation: Animation) {
        outAnimation = null
        inAnimation = null
        isFlipping = false
    }

    override fun onAnimationStart(animation: Animation) {}

    override fun onAnimationRepeat(animation: Animation) {}
}