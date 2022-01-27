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
open class TornadoPath(var particle: ParticleData, var center: Location) : SubPrimaryAnimation {
   var maxHeight = 8f
   var maxRadius = 3f
   var lines = 4
   var heightGrow = 0.5f
   var radiusGrow = maxHeight / maxRadius
   var stopDelay = -1
   
   override fun animate(animation: Animation, ticker: Tick) {
      val step = ticker.step
      
      for (l in 1..lines) {
         var y = 0.0
         while (y < maxHeight) {
            val radius = y * radiusGrow
            val x = cos(radians((360/lines*l) + y*25 - step)) * radius
            val z = sin(radians((360/lines*l) + y*25 - step)) * radius
            particle.play(center.clone().add(x, y, z))
            y += heightGrow
         }
      }
      
      reset(animation, ticker)
   }
   
   override fun reset(animation: Animation, ticker: Tick) {
      if (stopDelay > 0 && animation.ticks % stopDelay == 0) ticker.stopTick()
   }
}

/**
 * Adds a new [TornadoPath] sub animation.
 */
inline fun StandAnimation.tornado(
   particle: ParticleData,
   start: Location = location,
   block: TornadoPath.() -> Unit
): TornadoPath = addSub(TornadoPath(particle, start).apply(block))

/**
 * Adds a new [TornadoPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.tornado(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
   block: TornadoPath.() -> Unit
): TornadoPath = addSub(ticks, TornadoPath(particle, start).apply(block))

/**
 * Adds a new [TornadoPath] sub animation.
 */
fun StandAnimation.tornado(
   particle: ParticleData,
   start: Location = location,
): TornadoPath = addSub(TornadoPath(particle, start))

/**
 * Adds a new [TornadoPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.tornado(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
): TornadoPath = addSub(ticks, TornadoPath(particle, start))

/**
 * Adds a new [TornadoPath] sub animation.
 */
inline fun StandAnimation.tornado(
   particle: Particle,
   start: Location = location,
   block: TornadoPath.() -> Unit
): TornadoPath = addSub(TornadoPath(particle(particle), start).apply(block))

/**
 * Adds a new [TornadoPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.tornado(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: TornadoPath.() -> Unit
): TornadoPath = addSub(ticks, TornadoPath(particle(particle), start).apply(block))

/**
 * Adds a new [TornadoPath] sub animation.
 */
fun StandAnimation.tornado(
   particle: Particle,
   start: Location = location,
): TornadoPath = addSub(TornadoPath(particle(particle), start))

/**
 * Adds a new [TornadoPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.tornado(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): TornadoPath = addSub(ticks, TornadoPath(particle(particle), start))
