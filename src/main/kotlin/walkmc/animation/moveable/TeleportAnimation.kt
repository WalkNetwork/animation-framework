package walkmc.animation.moveable

import net.minecraft.server.World
import org.bukkit.*
import org.bukkit.inventory.ItemStack
import walkmc.*
import walkmc.animation.*
import walkmc.collections.*
import walkmc.extensions.*
import walkmc.shape.*

/**
 * Represents a stand teleport animation.
 */
open class TeleportAnimation(world: World?) : BaseStandAnimation(world) {
   
   var phases = IndexList<Location>()
   
   override fun tick() {
      super.tick()
      teleportTo(phases.toNextOrFirst(), false)
   }
   
   override fun withItem(item: ItemStack) {
      setEquipment(4, item.handlerCopy())
   }
   
   fun addPhase(location: Location) {
      phases += location
   }
}

/**
 * Creates a new [TeleportAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun teleportAnimation(location: Location, block: TeleportAnimation.() -> Unit): TeleportAnimation {
   return TeleportAnimation(null).apply {
      block()
      spawnInWorld(location, false)
      start()
   }
}

/**
 * Creates a new [TeleportAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 *
 * @param stopAfter - Stops this animation after the given amount of seconds.
 */
inline fun teleportAnimation(
   location: Location,
   stopAfter: Int,
   block: TeleportAnimation.() -> Unit
): TeleportAnimation {
   return TeleportAnimation(null).apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}


fun main() {
   val location by notnull<Location>()
   
   moveableAnimation(location, 10) {
      allowReverseOrder = true
      max = 20
      onTick { Particle.FLAME.play(localization down 0.4) }
   }
   
   teleportAnimation(location, 10) {
      phases += location.drawCirclePath(3.0, 25)
      withMaterial(Materials.REDSTONE)
   }
   
}
