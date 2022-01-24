package walkmc.animation.impl

import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.inventory.*
import walkmc.animation.*
import walkmc.animation.extensions.*
import walkmc.animation.stand.*
import walkmc.collections.*
import walkmc.extensions.*

/**
 * Represents a stand teleport animation.
 */
open class TeleportAnimation : BaseStandAnimation() {
   
   var phases = IndexList<Location>()
   
   override fun tick() {
      move(phases.toNextOrFirst())
      super.tick()
   }
   
   override fun withItem(item: ItemStack) {
      head = item
   }
   
   override fun click(player: Player, slot: Int) {
      if (slot == 4) for (clicker in clickers) clicker(player, slot)
   }
   
   override fun offset() = localization up headHeight + 0.15
   
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
   return TeleportAnimation().apply {
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
   return TeleportAnimation().apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}
