@file:Suppress("NOTHING_TO_INLINE")

package walkmc.animation.interfaces

import com.soywiz.korma.geom.*
import org.bukkit.util.*

/**
 * Represents an object that's can rotate.
 */
interface Rotatable {
   
   /**
    * The amount of rotations done.
    */
   var rotations: Int
   
   /**
    * The amount of max rotations until the stop of the animation.
    */
   var maxRotations: Int
   
   /**
    * If the autorotation cycle is allowed.
    */
   var autoRotate: Boolean
   
   /**
    * The force for auto rotating the X position.
    *
    * This is only used if [autoRotate] is true.
    */
   var rotateForceX: Float
   
   /**
    * The force for auto rotating the Y position.
    *
    * This is only used if [autoRotate] is true.
    */
   var rotateForceY: Float
   
   /**
    * The force for auto rotating the Z position.
    *
    * This is only used if [autoRotate] is true.
    */
   var rotateForceZ: Float
   
   /**
    * The current rotation X position.
    */
   val rotateX: Float
   
   /**
    * The current rotation Y position.
    */
   val rotateY: Float
   
   /**
    * The current rotation Z position.
    */
   val rotateZ: Float
   
   /**
    * Rotates XYZ position of this object.
    */
   fun rotate(x: Float, y: Float, z: Float)
   
   /**
    * Rotates the XYZ position of this object with values setted in auto force.
    *
    * @see rotate
    * @see rotateForceX
    * @see rotateForceY
    * @see rotateForceZ
    */
   fun rotateAuto() {
      rotate(rotateForceX, rotateForceY, rotateForceZ)
   }
   
   /**
    * Configures the XYZ rotation force.
    */
   fun force(x: Float, y: Float, z: Float) {
      rotateForceX = x
      rotateForceY = y
      rotateForceZ = z
   }
}

/**
 * Gets a bukkit vector displaying the rotation of this object.
 */
inline val Rotatable.rotVector get() = Vector(rotateX, rotateY, rotateZ)

/**
 * Gets a [Vector3] displaying the rotation of this object.
 */
inline val Rotatable.rotVector3 get() = Vector3(rotateX, rotateY, rotateZ)

//
// Rotation functions
//

inline fun Rotatable.rotate(x: Double, y: Double, z: Double) = rotate(x.toFloat(), y.toFloat(), z.toFloat())
inline fun Rotatable.rotate(x: Int, y: Int, z: Int) = rotate(x.toFloat(), y.toFloat(), z.toFloat())

inline fun Rotatable.rotateX(x: Double) = rotate(x.toFloat(), 0f, 0f)
inline fun Rotatable.rotateX(x: Float) = rotate(x, 0f, 0f)
inline fun Rotatable.rotateX(x: Int) = rotate(x.toFloat(), 0f, 0f)

inline fun Rotatable.rotateY(y: Double) = rotate(0f, y.toFloat(), 0f)
inline fun Rotatable.rotateY(y: Float) = rotate(0f, y, 0f)
inline fun Rotatable.rotateY(y: Int) = rotate(0f, y.toFloat(), 0f)

inline fun Rotatable.rotateZ(z: Double) = rotate(0f, 0f, z.toFloat())
inline fun Rotatable.rotateZ(z: Float) = rotate(0f, 0f, z)
inline fun Rotatable.rotateZ(z: Int) = rotate(0f, 0f, z.toFloat())
