package com.lucidware.planningpokercards.common.animations

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.Interpolator

/**
 * Created by Paweł Świętochowski.
 */
object AnimationFactory {

    fun getFlipAnimation(
            fromView: View,
            dir: FlipDirection,
            duration: Long,
            interpolator: Interpolator,
            animationListener: Animation.AnimationListener
    ): Array<Animation> {
        val centerX = fromView.width / 2.0f
        val centerY = fromView.height / 2.0f

        val outFlip = FlipAnimation(
                dir.startDegreeForFirstView,
                dir.endDegreeForFirstView,
                centerX,
                centerY,
                FlipAnimation.DEFAULT_SCALE,
                ScaleUpDown.SCALE_DOWN
        )
        outFlip.duration = duration
        outFlip.fillAfter = true
        outFlip.interpolator = interpolator

        val outAnimation = AnimationSet(true)
        outAnimation.addAnimation(outFlip)

        outAnimation.setAnimationListener(object : AnimationListener() {
            override fun onAnimationStart(animation: Animation) {
                animationListener.onAnimationStart(animation)
            }
        })

        val inFlip = FlipAnimation(
                dir.startDegreeForSecondView,
                dir.endDegreeForSecondView,
                centerX,
                centerY,
                FlipAnimation.DEFAULT_SCALE,
                ScaleUpDown.SCALE_UP
        )
        inFlip.duration = duration
        inFlip.fillAfter = true
        inFlip.interpolator = interpolator
        inFlip.startOffset = duration

        val inAnimation = AnimationSet(true)
        inAnimation.addAnimation(inFlip)

        inAnimation.setAnimationListener(object : AnimationListener() {
            override fun onAnimationEnd(animation: Animation) {
                animationListener.onAnimationEnd(animation)
            }
        })

        return arrayOf(outAnimation, inAnimation)
    }
}