package walkmc.animation.sub

import org.bukkit.*
import org.bukkit.util.*
import walkmc.*
import walkmc.animation.*
import walkmc.animation.extensions.*
import walkmc.animation.interfaces.*
import walkmc.animation.stand.*
import kotlin.math.*

/**
 * Represents a vortex particle path sub animation.
 */
open class VortexPath(var particle: ParticleData, var center: Location) : SubPrimaryAnimation {
   var circles = 3
   var helixes = 4
   var radius = 2.0
   var torsion = 0.0
   var max = 3.0
   var torsionGrow = 0.5
   var radials = PI / 16
   var angleX = 0f
   var angleY = 0f
   var angleZ = 0f
   var allowReverseOrder = true
   var isInReverseOrder = false
   
   override fun animate(animation: Animation, ticker: Tick) {
      torsion += if (!isInReverseOrder) torsionGrow else -torsionGrow

      for (i in 1..circles) {
         for (j in 1..helixes) {
            val angle = ticker.step * radials + (2 * PI * j / helixes)
            particle.play(center.clone()
               .add(Vector(cos(angle) * radius, torsion, sin(angle) * radius).rotate(angleX, angleY, angleZ)))
         }
      }
      
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
 * Adds a new [VortexPath] sub animation.
 */
inline fun StandAnimation.vortex(
   particle: ParticleData,
   start: Location = location,
   block: VortexPath.() -> Unit
): VortexPath = addSub(VortexPath(particle, start).apply(block))

/**
 * Adds a new [VortexPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.vortex(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
   block: VortexPath.() -> Unit
): VortexPath = addSub(ticks, VortexPath(particle, start).apply(block))

/**
 * Adds a new [VortexPath] sub animation.
 */
fun StandAnimation.vortex(
   particle: ParticleData,
   start: Location = location,
): VortexPath = addSub(VortexPath(particle, start))

/**
 * Adds a new [VortexPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.vortex(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
): VortexPath = addSub(ticks, VortexPath(particle, start))

/**
 * Adds a new [VortexPath] sub animation.
 */
inline fun StandAnimation.vortex(
   particle: Particle,
   start: Location = location,
   block: VortexPath.() -> Unit
): VortexPath = vortex(particle(particle), start, block)

/**
 * Adds a new [VortexPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.vortex(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: VortexPath.() -> Unit
): VortexPath = vortex(ticks, particle(particle), start, block)

/**
 * Adds a new [VortexPath] sub animation.
 */
fun StandAnimation.vortex(
   particle: Particle,
   start: Location = location,
): VortexPath = vortex(particle(particle), start)

/**
 * Adds a new [VortexPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.vortex(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): VortexPath = vortex(ticks, particle(particle), start)
