package walkmc.animation.interfaces

import walkmc.animation.*

/**
 * Represents a tick action for animations.
 */
open class Tick(val animation: Animation, val action: Tick.() -> Unit) : () -> Unit {
   
   /**
    * Amount of steps counts of this ticker.
    */
   var step = 0
   
   /**
    * Verify if this ticker has maden any steps.
    */
   val hasStarted: Boolean
      get() = step > 0
   
   /**
    * Verify if this ticker is in the first step.
    */
   val isFirstStep: Boolean
      get() = step == 1
   
   override fun invoke() {
      step++
      action()
   }
   
   /**
    * Removes this tick action from [animation].
    *
    * Uses this to improve performance when a tick action is not more util.
    */
   fun stopTick() {
      animation.tickers -= this
   }
}
