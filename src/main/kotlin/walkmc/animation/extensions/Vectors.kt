package walkmc.animation.extensions

import org.bukkit.util.*
import kotlin.math.*

/**
 * Rotates the X axis of this vector by the given [cos] and [sin].
 */
fun Vector.rotateX(cos: Double, sin: Double): Vector = setY(y * cos - z * sin).setZ(y * sin + z * cos)

/**
 * Rotates the X axis of this vector by [angle] amount.
 */
fun Vector.rotateX(angle: Double): Vector {
   val radians = radians(angle)
   return rotateX(cos(radians), sin(radians))
}

/**
 * Rotates the Y axis of this vector by the given [cos] and [sin].
 */
fun Vector.rotateY(cos: Double, sin: Double): Vector = setX(x * cos + z * sin).setZ(x * -sin + z * cos)

/**
 * Rotates the Y axis of this vector by [angle] amount.
 */
fun Vector.rotateY(angle: Double): Vector {
   val radians = radians(-angle)
   return rotateY(cos(radians), sin(radians))
}

/**
 * Rotates the Z axis of this vector by the given [cos] and [sin].
 */
fun Vector.rotateZ(cos: Double, sin: Double): Vector = setX(x * cos - y * sin).setY(x * sin + y * cos)

/**
 * Rotates the Z axis of this vector by [angle] amount.
 */
fun Vector.rotateZ(angle: Double): Vector {
   val radians = radians(angle)
   return rotateZ(cos(radians), sin(radians))
}
