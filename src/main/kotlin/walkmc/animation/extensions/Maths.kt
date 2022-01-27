@file:Suppress("NOTHING_TO_INLINE")

package walkmc.animation.extensions

inline fun radians(n: Double) = Math.toRadians(n)
inline fun radians(n: Float) = Math.toRadians(n.toDouble())
inline fun radians(n: Int) = Math.toRadians(n.toDouble())

const val SQRT_3 = 1.73205f
