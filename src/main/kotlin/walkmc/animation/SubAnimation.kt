package walkmc.animation

import walkmc.animation.interfaces.*

typealias SubPrimaryAnimation = SubAnimation<Animation>

/**
 * Subs animations is an animation that's resides animations.
 */
interface SubAnimation<T : Animation> {
   
   /**
    * Animates this sub animation.
    */
   fun animate(animation: T, ticker: Tick)
   
   /**
    * Resets this sub animation
    */
   fun reset(animation: T, ticker: Tick) = Unit
}
