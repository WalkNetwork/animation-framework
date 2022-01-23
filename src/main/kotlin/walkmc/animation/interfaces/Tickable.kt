package walkmc.animation.interfaces

import walkmc.ticker.*

/**
 * Provides properties and functions to be able the tick of an object.
 */
interface Tickable : Ticker {
   
   /**
    * Delay between [tick] calls.
    */
   var delay: Int
   
   /**
    * Amount of ticks elapsed.
    */
   var ticks: Int
}
