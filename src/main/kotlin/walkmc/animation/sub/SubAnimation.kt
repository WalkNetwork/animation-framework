package walkmc.animation.sub

import walkmc.animation.*

/**
 * Subs animations is an animation that's resides
 * in primary animations: [Animation].
 */
interface SubAnimation {
   
   /**
    * Animates this sub animation.
    */
   fun animate(primary: Animation)
}
