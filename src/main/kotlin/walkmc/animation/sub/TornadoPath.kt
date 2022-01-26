package walkmc.animation.sub

import org.bukkit.*
import walkmc.*
import walkmc.animation.*
import walkmc.animation.extensions.*
import walkmc.animation.interfaces.*
import walkmc.animation.stand.*
import kotlin.math.*

/**
 * Represents a tornado particle path sub animation.
 */
open class TornadoPath(var particle: Particle, var center: Location) : SubPrimaryAnimation {
   var angle = 0.0
   var maxHeight = 8.0
   var maxRadius = 3.0
   var lines = 4
   var heightInc = 0.5
   var radiusInc = maxHeight / maxRadius
   var stopDelay = -1
   
   override fun animate(animation: Animation, ticker: Tick) {
      for (l in 1..lines) {
         var y = 0.0
         while (y < maxHeight) {
            val radius = y * radiusInc
            val x = cos(radians((360/lines*l) + y*25 - angle)) * radius
            val z = sin(radians((360/lines*l) + y*25 - angle)) * radius
            particle.play(center.clone().add(x, y, z))
            y += heightInc
         }
      }
      
      angle++
      if (stopDelay > 0 && animation.ticks % stopDelay == 0) ticker.stopTick()
   }
}

/**
 * Adds a new [TornadoPath] sub animation.
 */
inline fun StandAnimation.tornado(
   particle: Particle,
   start: Location = location,
   block: TornadoPath.() -> Unit
): TornadoPath = addSub(TornadoPath(particle, start).apply(block))

/**
 * Adds a new [TornadoPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.tornado(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: TornadoPath.() -> Unit
): TornadoPath = addSub(ticks, TornadoPath(particle, start).apply(block))

/**
 * Adds a new [TornadoPath] sub animation.
 */
fun StandAnimation.tornado(
   particle: Particle,
   start: Location = location,
): TornadoPath = addSub(TornadoPath(particle, start))

/**
 * Adds a new [TornadoPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.tornado(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): TornadoPath = addSub(ticks, TornadoPath(particle, start))
