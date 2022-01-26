package walkmc.animation.sub

import org.bukkit.*
import walkmc.*
import walkmc.animation.*
import walkmc.animation.interfaces.*
import walkmc.animation.stand.*
import walkmc.extensions.*
import kotlin.math.*

/**
 * Represents a Dampened Waves particle path sub animation.
 */
open class DampenedWavesPath(var particle: Particle, var center: Location) : SubPrimaryAnimation {
   var max = 16
   var torsion = 0.0
   var distance = 1.0
   var torsionFetcher = 0.1 * PI
   var thetaMax = 2 * PI
   var thetaFetcher = PI / 8
   var offsetY = 1.5
   var repeat = false
   
   override fun animate(animation: Animation, ticker: Tick) {
      torsion += torsionFetcher
      val y = exp(-0.1 * torsion) * sin(torsion) + offsetY
      
      var theta = 0.0
      while (theta < thetaMax) {
         center.clone()
            .add(torsion * cos(theta) * distance, y, torsion * sin(theta) * distance)
            .playParticle(particle)
         theta += thetaFetcher
      }
      
      if (torsion > max)
         if (!repeat) ticker.stopTick() else torsion = 0.0
   }
}

/**
 * Adds a new [DampenedWavesPath] sub animation.
 */
inline fun StandAnimation.dampenedWaves(
   particle: Particle,
   start: Location = location,
   block: DampenedWavesPath.() -> Unit
): DampenedWavesPath = addSub(DampenedWavesPath(particle, start).apply(block))

/**
 * Adds a new [DampenedWavesPath] sub animation with an interval of [ticks].
 */
inline fun StandAnimation.dampenedWaves(
   ticks: Int,
   particle: Particle,
   start: Location = location,
   block: DampenedWavesPath.() -> Unit
): DampenedWavesPath = addSub(ticks, DampenedWavesPath(particle, start).apply(block))

/**
 * Adds a new [DampenedWavesPath] sub animation.
 */
fun StandAnimation.dampenedWaves(
   particle: Particle,
   start: Location = location,
): DampenedWavesPath = addSub(DampenedWavesPath(particle, start))

/**
 * Adds a new [DampenedWavesPath] sub animation with an interval of [ticks].
 */
fun StandAnimation.dampenedWaves(
   ticks: Int,
   particle: Particle,
   start: Location = location,
): DampenedWavesPath = addSub(ticks, DampenedWavesPath(particle, start))