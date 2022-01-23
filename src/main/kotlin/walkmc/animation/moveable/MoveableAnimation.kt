package walkmc.animation.moveable

import net.minecraft.server.World
import org.bukkit.*
import org.bukkit.inventory.ItemStack
import walkmc.animation.*
import walkmc.extensions.*

/**
 * Represents a moveable stand animation.
 */
open class MoveableAnimation(world: World?) : BaseStandAnimation(world) {
   
   var count = 0
   var max = 20
   var offsetX = 0.0
   var offsetY = 0.08
   var offsetZ = 0.0
   var allowReverseOrder = false
   var isInReverseOrder = false
   
   override fun tick() {
      if (!allowReverseOrder) {
         if (++count >= max) stop()
      } else {
         if (!isInReverseOrder) {
            if (++count >= max) isInReverseOrder = true
         } else {
            if (-max >= --count) isInReverseOrder = false
         }
      }
      
      move(relative(localization))
      super.tick()
   }
   
   override fun withItem(item: ItemStack) {
      setEquipment(4, item.handlerCopy())
   }
   
   fun relative(location: Location): Location {
      return if (!isInReverseOrder) {
         location.getRelative(offsetX, offsetY, offsetZ)
      } else {
         location.getRelative(-offsetX, -offsetY, -offsetZ)
      }
   }
}

/**
 * Creates a new [MoveableAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun moveableAnimation(location: Location, block: MoveableAnimation.() -> Unit): MoveableAnimation {
   return MoveableAnimation(null).apply {
      block()
      spawnInWorld(location, false)
      start()
   }
}

/**
 * Creates a new [MoveableAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 *
 * @param stopAfter - Stops this animation after the given amount of seconds.
 */
inline fun moveableAnimation(
   location: Location,
   stopAfter: Int,
   block: MoveableAnimation.() -> Unit
): MoveableAnimation {
   return MoveableAnimation(null).apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}
