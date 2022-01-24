package walkmc.animation.sub

import walkmc.animation.*
import walkmc.animation.interfaces.*

typealias SubPrimaryAnimation = SubAnimation<Animation>

/**
 * Subs animations is an animation that's resides
 * in primary animations: [Animation].
 */
interface SubAnimation<T : Animation> {
   
   /**
    * Animates this sub animation.
    */
   fun animate(animation: T, ticker: Tick)
}
