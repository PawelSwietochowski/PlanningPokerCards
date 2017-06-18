package com.lucidware.planningpokercards.common.animations

/**
 * Created by Paweł Świętochowski.
 */
enum class FlipDirection {
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT;

    val startDegreeForFirstView = 0f

    val endDegreeForFirstView: Float
        get() {
            return when (this) {
                LEFT_TO_RIGHT -> 90f
                RIGHT_TO_LEFT -> -90f
                else -> 0f
            }
        }

    val startDegreeForSecondView: Float
        get() {
            return when (this) {
                LEFT_TO_RIGHT -> -90f
                RIGHT_TO_LEFT -> 90f
                else -> return 0f
            }
        }

    val endDegreeForSecondView = 0f

    fun reversed(): FlipDirection {
        return when (this) {
            LEFT_TO_RIGHT -> RIGHT_TO_LEFT
            RIGHT_TO_LEFT -> LEFT_TO_RIGHT
        }
    }
}