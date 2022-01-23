package walkmc.animation

import walkmc.animation.interfaces.Tickable
import walkmc.animation.sub.*
import walkmc.interfaces.*

typealias TickSet = MutableSet<() -> Unit>
typealias StartSet = MutableSet<() -> Unit>
typealias StopSet = MutableSet<() -> Unit>

/**
 * Base interface for any animation.
 *
 * The purpose of this interface is for fast and object-oriented
 * creation of new animations.
 */
interface Animation : Startable, Stoppable, Tickable {
   
   /**
    * All tick listeners registered.
    */
   var tickers: TickSet
   
   /**
    * All start listeners registered.
    */
   var starters: StartSet
   
   /**
    * All stop listeners registered.
    */
   var stoppers: StopSet
   
   /**
    * Add a tick listener to be executed.
    */
   fun onTick(action: () -> Unit) {
      tickers += action
   }
   
   /**
    * Add a start listener to be executed.
    */
   fun onStart(action: () -> Unit) {
      starters += action
   }
   
   /**
    * Add a stop listener to be executed.
    */
   fun onStop(action: () -> Unit) {
      stoppers += action
   }
   
   /**
    * Add a sub animation to be tracked.
    */
   fun <T : SubAnimation> addSub(animation: T): T {
      onTick { animation.animate(this) }
      return animation
   }
}

/**
 * Add a sub animation to be tracked.
 */
fun <T : SubAnimation> Animation.addSub(tick: Int, animation: T): T {
   onTick(tick) { animation.animate(this) }
   return animation
}

/**
 * Adds a new tick listener that's will be executed every [amount] of ticks.
 */
inline fun Animation.onTick(amount: Int, crossinline action: Animation.() -> Unit) =
   onTick { if (ticks % amount == 0) action() }

/**
 * Adds a new tick listener that's will be executed every tick in the specified [range].
 */
inline fun Animation.onTick(range: IntRange, crossinline action: Animation.() -> Unit) =
   onTick { if (ticks in range) action() }

/**
 * Adds a new tick listener that's will be executed in the first [amount] of ticks.
 */
inline fun Animation.onFirstTick(amount: Int, crossinline action: Animation.() -> Unit) =
   onTick { if (ticks == amount) action() }

/**
 * Adds a new tick listener that's will be executed every second, or 20 ticks.
 */
inline fun Animation.onEverySecond(crossinline action: Animation.() -> Unit) =
   onTick { if (ticks % 20 == 0) action() }

/**
 * Adds a new tick listener that's will be executed [amount] of seconds.
 */
inline fun Animation.onEverySecond(amount: Int, crossinline action: Animation.() -> Unit) =
   onTick { if (ticks % (amount * 20) == 0) action() }

/**
 * Adds a new tick listener that's will be executed in the first second.
 */
inline fun Animation.onFirstSecond(crossinline action: Animation.() -> Unit) =
   onTick { if (ticks == 20) action() }

/**
 * Adds a new tick listener that's will be executed in the first [amount] of seconds.
 */
inline fun Animation.onFirstSecond(amount: Int, crossinline action: Animation.() -> Unit) =
   onTick { if (ticks == amount * 20) action() }

/**
 * Stops this question after [amount] of ticks.
 */
fun Animation.stopAfter(amount: Int) = onTick { if (ticks == amount) stop() }

/**
 * Stops this question after [amount] of ticks and executes [action].
 */
inline fun Animation.stopAfter(amount: Int, crossinline action: Animation.() -> Unit) =
   onTick {
      if (ticks == amount) {
         action()
         stop()
      }
   }
