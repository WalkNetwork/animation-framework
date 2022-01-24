package walkmc.animation.interfaces

import walkmc.ticker.*

/**
 * Provides properties and functions to be able the tick of an object.
 */
interface Tickable : Ticker {
   
   /**
    * Amount of ticks elapsed.
    */
   val ticks: Int
}
