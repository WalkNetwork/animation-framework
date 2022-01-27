package walkmc.animation.extensions

import org.bukkit.util.*
import kotlin.math.*

fun Vector.rotateX(cos: Double, sin: Double): Vector = setY(y * cos - z * sin).setZ(y * sin + z * cos)
fun Vector.rotateX(cos: Float, sin: Float): Vector = setY(y * cos - z * sin).setZ(y * sin + z * cos)
fun Vector.rotateX(cos: Int, sin: Int): Vector = setY(y * cos - z * sin).setZ(y * sin + z * cos)
fun Vector.rotateX(angle: Double): Vector {
   if (angle == 0.0) return this
   val radians = radians(angle)
   return rotateX(cos(radians), sin(radians))
}

fun Vector.rotateX(angle: Float): Vector {
   if (angle == 0f) return this
   val radians = radians(angle)
   return rotateX(cos(radians), sin(radians))
}

fun Vector.rotateX(angle: Int): Vector {
   if (angle == 0) return this
   val radians = radians(angle)
   return rotateX(cos(radians), sin(radians))
}

fun Vector.rotateY(cos: Double, sin: Double): Vector = setX(x * cos + z * sin).setZ(x * -sin + z * cos)
fun Vector.rotateY(cos: Float, sin: Float): Vector = setX(x * cos + z * sin).setZ(x * -sin + z * cos)
fun Vector.rotateY(cos: Int, sin: Int): Vector = setX(x * cos + z * sin).setZ(x * -sin + z * cos)
fun Vector.rotateY(angle: Double): Vector {
   if (angle == 0.0) return this
   val radians = radians(-angle)
   return rotateY(cos(radians), sin(radians))
}

fun Vector.rotateY(angle: Float): Vector {
   if (angle == 0f) return this
   val radians = radians(-angle)
   return rotateY(cos(radians), sin(radians))
}

fun Vector.rotateY(angle: Int): Vector {
   if (angle == 0) return this
   val radians = radians(-angle)
   return rotateY(cos(radians), sin(radians))
}

fun Vector.rotateZ(cos: Double, sin: Double): Vector = setX(x * cos - y * sin).setY(x * sin + y * cos)
fun Vector.rotateZ(cos: Float, sin: Float): Vector = setX(x * cos - y * sin).setY(x * sin + y * cos)
fun Vector.rotateZ(cos: Int, sin: Int): Vector = setX(x * cos - y * sin).setY(x * sin + y * cos)
fun Vector.rotateZ(angle: Double): Vector {
   if (angle == 0.0) return this
   val radians = radians(angle)
   return rotateZ(cos(radians), sin(radians))
}

fun Vector.rotateZ(angle: Float): Vector {
   if (angle == 0f) return this
   val radians = radians(angle)
   return rotateZ(cos(radians), sin(radians))
}

fun Vector.rotateZ(angle: Int): Vector {
   if (angle == 0) return this
   val radians = radians(angle)
   return rotateZ(cos(radians), sin(radians))
}

fun Vector.rotate(x: Double, y: Double, z: Double): Vector {
   return rotateX(x).rotateY(y).rotateZ(z)
}

fun Vector.rotate(x: Float, y: Float, z: Float): Vector {
   return rotateX(x).rotateY(y).rotateZ(z)
}

fun Vector.rotate(x: Int, y: Int, z: Int): Vector {
   return rotateX(x).rotateY(y).rotateZ(z)
}
