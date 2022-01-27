package walkmc.animation.sub

import org.bukkit.*
import walkmc.*
import walkmc.animation.*
import walkmc.animation.interfaces.*

/**
 * Represents a particle path sub animation.
 */
open class ParticlePath(var particle: ParticleData, val path: List<Location>) : SubPrimaryAnimation {
   var current = 0
   
   override fun animate(animation: Animation, ticker: Tick) {
      if (current >= path.size) current = 0
      particle.play(path[current++])
   }
}

/**
 * Adds a new [ParticlePath] sub animation.
 */
fun Animation.particlePath(
   particle: ParticleData,
   path: List<Location>
): ParticlePath = addSub(ParticlePath(particle, path))

/**
 * Adds a new [ParticlePath] sub animation with an interval of [ticks].
 */
fun Animation.particlePath(
   ticks: Int,
   particle: ParticleData,
   path: List<Location>
): ParticlePath = addSub(ticks, ParticlePath(particle, path))

/**
 * Adds a new [ParticlePath] sub animation.
 */
fun Animation.particlePath(
   particle: Particle,
   path: List<Location>
): ParticlePath = addSub(ParticlePath(particle(particle), path))

/**
 * Adds a new [ParticlePath] sub animation with an interval of [ticks].
 */
fun Animation.particlePath(
   ticks: Int,
   particle: Particle,
   path: List<Location>
): ParticlePath = addSub(ticks, ParticlePath(particle(particle), path))
