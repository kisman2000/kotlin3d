package utils

import kotlin.math.sqrt

const val ALMOST_ZERO = 1e-4f

operator fun Number.rangeTo(
    other : Number
) : ClosedRange<Double> = this.toDouble()..other.toDouble()

//TODO: cached sqrt
fun hypotenuse(
    a : Number,
    b : Number
) = sqrt(a.toDouble() * a.toDouble() + b.toDouble() * b.toDouble())