package com.lucidware.planningpokercards.common.animations

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Created by Paweł Świętochowski.
 */
class FlipAnimation(
    private val fromDegrees: Float,
    private val toDegrees: Float,
    private val centerX: Float,
    private val centerY: Float,
    scale: Float,
    private val scaleType: ScaleUpDown
) : Animation() {

    private lateinit var camera: Camera
    private val scale: Float

    init {
        this.scale = if (scale <= 0 || scale >= 1) DEFAULT_SCALE else scale
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        camera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        val degrees = fromDegrees + ((toDegrees - fromDegrees) * interpolatedTime)
        val matrix = transformation.matrix

        camera.save()
        camera.rotateY(degrees)
        camera.getMatrix(matrix)
        camera.restore()

        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
        matrix.preScale(
            scaleType.getCurrentZoomLevel(scale, interpolatedTime),
            scaleType.getCurrentZoomLevel(scale, interpolatedTime),
            centerX,
            centerY
        )
    }

    companion object {
        const val DEFAULT_SCALE = 0.6f
    }
}