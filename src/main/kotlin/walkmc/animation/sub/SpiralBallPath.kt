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
 * Represents a spiral ball particle path sub animation.
 */
open class SpiralBallPath(var particle: ParticleData, var center: Location) : SubPrimaryAnimation {
   var quantity = 150
   var quantityByIteration = 10
   var size = 1f
   
   var factorX = 1f
   var factorY = 2f
   var factorZ = 1f
   
   var offsetX = 0f
   var offsetY = 0.8f
   var offsetZ = 0f
   
   var angleX = 0f
   var angleY = 0f
   var angleZ = 0f
   
   var step = 0
   
   override fun animate(animation: Animation, ticker: Tick) {
      val vector = Vector()
      for (i in 1..quantityByIteration) {
         step++
         
         val t = (PI / quantity) * step
         val r = sin(t) * size
         val s = 2 * PI * t
         
         vector.apply {
            x = factorX * r * cos(s) + offsetX
            y = factorY * size * cos(t) + offsetY
            z = factorZ * r * sin(s) + offsetZ
            rotate(angleX, angleY, angleZ)
         }
         
         particle.play(center.clone().add(vector))
      }
   }
}

/**
 * Adds a new [SpiralBallPath] sub animation.
 */
inline fun StandAnimation.spiralBall(
   particle: ParticleData,
   start: Location = location,
   block: SpiralBallPath.() -> Unit
): SpiralBallPath = addSub(SpiralBallPath(particle, start).apply(block))

/**
 * Adds a new [SpiralBallPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.spiralBall(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
   block: SpiralBallPath.() -> Unit
): SpiralBallPath = addSub(ticks, SpiralBallPath(particle, start).apply(block))

/**
 * Adds a new [SpiralBallPath] sub animation.
 */
fun StandAnimation.spiralBall(
   particle: ParticleData,
   start: Location = location,
): SpiralBallPath = addSub(SpiralBallPath(particle, start))

/**
 * Adds a new [SpiralBallPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.spiralBall(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
): SpiralBallPath = addSub(ticks, SpiralBallPath(particle, start))

/**
 * Adds a new [SpiralBallPath] sub animation.
 */
inline fun StandAnimation.spiralBall(
   particle: Particle,
   start: Location = location,
   block: SpiralBallPath.() -> Unit
): SpiralBallPath = spiralBall(particle(particle), start, block)

/**
 * Adds a new [SpiralBallPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.spiralBall(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: SpiralBallPath.() -> Unit
): SpiralBallPath = spiralBall(ticks, particle(particle), start, block)

/**
 * Adds a new [SpiralBallPath] sub animation.
 */
fun StandAnimation.spiralBall(
   particle: Particle,
   start: Location = location,
): SpiralBallPath = spiralBall(particle(particle), start)

/**
 * Adds a new [SpiralBallPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.spiralBall(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): SpiralBallPath = spiralBall(ticks, particle(particle), start)

