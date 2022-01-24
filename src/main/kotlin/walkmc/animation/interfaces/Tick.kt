package walkmc.animation.interfaces

import walkmc.animation.*

/**
 * Represents a tick action for animations.
 */
open class Tick(val animation: Animation, val action: Tick.() -> Unit) : () -> Unit {
   override fun invoke() = action()
   
   /**
    * Removes this tick action from [animation].
    *
    * Uses this to improve performance when a tick action is not more util.
    */
   fun stopTick() {
      animation.tickers -= this
   }
}
