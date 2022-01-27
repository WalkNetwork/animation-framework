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
 * Represents a circle particle path sub animation.
 */
open class CirclePath(var particle: ParticleData, var center: Location) : SubPrimaryAnimation {
   var quantity = 20
   var radius = 0.4f
   
   var rotationX = 0.0
   var rotationY = 0.0
   var rotationZ = 0.0
   
   var angularX = PI / 200
   var angularY = PI / 170
   var angularZ = PI / 155
   
   var enableRotation = false
   var wholeCircle = false
   
   var step = 0
   
   override fun animate(animation: Animation, ticker: Tick) {
      val inc = (2 * PI) / quantity
      val amount = if (wholeCircle) quantity else 1
      for (i in 0..amount) {
         val angle = step * inc
         val vec = Vector(cos(angle) * radius, 0.0, sin(angle) * radius).rotate(rotationX, rotationY, rotationZ)
         if (enableRotation) vec.rotate(angularX * step, angularY * step, angularZ * step)
         particle.play(center.clone().add(vec))
         step++
      }
   }
}

/**
 * Adds a new [CirclePath] sub animation.
 */
inline fun StandAnimation.circle(
   particle: ParticleData,
   start: Location = location,
   block: CirclePath.() -> Unit
): CirclePath = addSub(CirclePath(particle, start).apply(block))

/**
 * Adds a new [CirclePath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.circle(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
   block: CirclePath.() -> Unit
): CirclePath = addSub(ticks, CirclePath(particle, start).apply(block))

/**
 * Adds a new [CirclePath] sub animation.
 */
fun StandAnimation.circle(
   particle: ParticleData,
   start: Location = location,
): CirclePath = addSub(CirclePath(particle, start))

/**
 * Adds a new [CirclePath] sub animation with an interval of [ticks].
 */
fun StandAnimation.circle(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
): CirclePath = addSub(ticks, CirclePath(particle, start))

/**
 * Adds a new [CirclePath] sub animation.
 */
inline fun StandAnimation.circle(
   particle: Particle,
   start: Location = location,
   block: CirclePath.() -> Unit
): CirclePath = circle(particle(particle), start, block)

/**
 * Adds a new [CirclePath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.circle(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: CirclePath.() -> Unit
): CirclePath = circle(ticks, particle(particle), start, block)

/**
 * Adds a new [CirclePath] sub animation.
 */
fun StandAnimation.circle(
   particle: Particle,
   start: Location = location,
): CirclePath = circle(particle(particle), start)

/**
 * Adds a new [CirclePath] sub animation with an interval of [ticks].
 */
fun StandAnimation.circle(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): CirclePath = circle(ticks, particle(particle), start)

