package com.lucidware.planningpokercards.common.animations

import android.view.animation.Animation

/**
 * Created by Paweł Świętochowski.
 */
abstract class AnimationListener : Animation.AnimationListener {

    override fun onAnimationRepeat(animation: Animation) {}

    override fun onAnimationEnd(animation: Animation) {}

    override fun onAnimationStart(animation: Animation) {}
}