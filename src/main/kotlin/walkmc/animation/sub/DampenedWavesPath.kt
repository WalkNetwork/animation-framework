package walkmc.animation.sub

import org.bukkit.*
import walkmc.*
import walkmc.animation.*
import walkmc.animation.interfaces.*
import walkmc.animation.stand.*
import kotlin.math.*

/**
 * Represents a Dampened Waves particle path sub animation.
 */
open class DampenedWavesPath(var particle: ParticleData, var center: Location) : SubPrimaryAnimation {
   var max = 16f
   var torsion = 0.0
   var distance = 1.0f
   var grow = 0.1 * PI
   var thetaMax = 2 * PI
   var thetaGrow = PI / 8
   var offsetY = 1.5f
   var repeat = false
   
   override fun animate(animation: Animation, ticker: Tick) {
      torsion += grow
      val y = exp(-0.1 * torsion) * sin(torsion) + offsetY
      var theta = 0.0
      while (theta < thetaMax) {
         particle.play(center.clone().add(torsion * cos(theta) * distance, y, torsion * sin(theta) * distance))
         theta += thetaGrow
      }
      
      reset(animation, ticker)
   }
   
   override fun reset(animation: Animation, ticker: Tick) {
      if (torsion > max) {
         if (!repeat) ticker.stopTick() else torsion = 0.0
      }
   }
}

/**
 * Adds a new [DampenedWavesPath] sub animation.
 */
inline fun StandAnimation.dampenedWaves(
   particle: ParticleData,
   start: Location = location,
   block: DampenedWavesPath.() -> Unit
): DampenedWavesPath = addSub(DampenedWavesPath(particle, start).apply(block))

/**
 * Adds a new [DampenedWavesPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.dampenedWaves(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
   block: DampenedWavesPath.() -> Unit
): DampenedWavesPath = addSub(ticks, DampenedWavesPath(particle, start).apply(block))

/**
 * Adds a new [DampenedWavesPath] sub animation.
 */
fun StandAnimation.dampenedWaves(
   particle: ParticleData,
   start: Location = location,
): DampenedWavesPath = addSub(DampenedWavesPath(particle, start))

/**
 * Adds a new [DampenedWavesPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.dampenedWaves(
   ticks: Int,
   particle: ParticleData,
   start: Location = location,
): DampenedWavesPath = addSub(ticks, DampenedWavesPath(particle, start))

/**
 * Adds a new [DampenedWavesPath] sub animation.
 */
inline fun StandAnimation.dampenedWaves(
   particle: Particle,
   start: Location = location,
   block: DampenedWavesPath.() -> Unit
): DampenedWavesPath = addSub(DampenedWavesPath(particle(particle), start).apply(block))

/**
 * Adds a new [DampenedWavesPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.dampenedWaves(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: DampenedWavesPath.() -> Unit
): DampenedWavesPath = addSub(ticks, DampenedWavesPath(particle(particle), start).apply(block))

/**
 * Adds a new [DampenedWavesPath] sub animation.
 */
fun StandAnimation.dampenedWaves(
   particle: Particle,
   start: Location = location,
): DampenedWavesPath = addSub(DampenedWavesPath(particle(particle), start))

/**
 * Adds a new [DampenedWavesPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.dampenedWaves(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): DampenedWavesPath = addSub(ticks, DampenedWavesPath(particle(particle), start))
