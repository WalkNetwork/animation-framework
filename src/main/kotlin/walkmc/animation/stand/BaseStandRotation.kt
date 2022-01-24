package walkmc.animation.stand

import walkmc.animation.interfaces.*

/**
 * Represents an abstract implementation of [StandAnimation] with [Rotatable].
 *
 * This works like a skeletal model for any stand rotation animation.
 */
abstract class BaseStandRotation : BaseStandAnimation(), Rotatable {
   
   override var rotations = 0
   override var maxRotations = Int.MAX_VALUE
   override var autoRotate = true
   override var rotateForceX = 0f
   override var rotateForceY = 0f
   override var rotateForceZ = 0f
   
   override fun tick() {
      for (action in tickers) action()
      if (autoRotate) rotateAuto()
   }
   
   override fun rotate(x: Float, y: Float, z: Float) {
      if (rotations++ >= maxRotations) stop()
   }
}
