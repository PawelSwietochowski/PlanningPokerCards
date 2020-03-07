package com.lucidware.planningpokercards.common.animations

/**
 * Created by Paweł Świętochowski.
 */
enum class ScaleUpDown {

    SCALE_UP,
    SCALE_DOWN,
    SCALE_CYCLE,
    SCALE_NONE;

    fun getCurrentZoomLevel(desiredZoom: Float, iteration: Float): Float {
        return when (this) {
            SCALE_UP -> desiredZoom + ((1 - desiredZoom) * iteration)
            SCALE_DOWN -> 1 - ((1 - desiredZoom) * iteration)
            SCALE_CYCLE -> {
                val halfWay = iteration > 0.5
                return if (halfWay) {
                    desiredZoom + ((1 - desiredZoom) * (iteration - 0.5f) * 2f)
                } else {
                    1 - ((1 - desiredZoom) * (iteration * 2))
                }
            }
            else -> 1f
        }
    }
}