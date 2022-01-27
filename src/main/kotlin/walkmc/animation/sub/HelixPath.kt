package walkmc.animation.sub

import org.bukkit.*
import walkmc.*
import walkmc.animation.*
import walkmc.animation.interfaces.*
import walkmc.animation.stand.*
import kotlin.math.*

/**
 * Represents a Helix particle path sub animation.
 */
open class HelixPath(var particle: Particle, var center: Location) : SubPrimaryAnimation {
   var max = 16f
   var torsion = 0.0
   var distance = 1f
   var torsionGrow = PI / 12
   var torsionOffsetY = 0.16f
   var allowReverseOrder = true
   var isInReverseOrder = false
   
   override fun animate(animation: Animation, ticker: Tick) {
      torsion += if (!isInReverseOrder) torsionGrow else -torsionGrow
      particle.play(center.clone().add(distance * cos(torsion), torsion * torsionOffsetY, distance * sin(torsion)))
      reset(animation, ticker)
   }
   
   override fun reset(animation: Animation, ticker: Tick) {
      if (!allowReverseOrder) {
         if (torsion > max) ticker.stopTick()
      } else {
         if (torsion > max) isInReverseOrder = true else if (torsion <= 0) isInReverseOrder = false
      }
   }
}

/**
 * Adds a new [HelixPath] sub animation.
 */
inline fun StandAnimation.helix(
   particle: Particle,
   start: Location = location,
   block: HelixPath.() -> Unit
): HelixPath = addSub(HelixPath(particle, start).apply(block))

/**
 * Adds a new [HelixPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.helix(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: HelixPath.() -> Unit
): HelixPath = addSub(ticks, HelixPath(particle, start).apply(block))

/**
 * Adds a new [HelixPath] sub animation.
 */
fun StandAnimation.helix(
   particle: Particle,
   start: Location = location,
): HelixPath = addSub(HelixPath(particle, start))

/**
 * Adds a new [HelixPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.helix(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): HelixPath = addSub(ticks, HelixPath(particle, start))
