package walkmc.animation.impl

import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.inventory.*
import walkmc.animation.*
import walkmc.animation.extensions.*
import walkmc.animation.stand.*

/**
 * Represents a stand item rotation animation.
 */
open class ItemRotation : BaseStandRotation() {
   
   override val rotateX get() = leftArmPose.x
   override val rotateY get() = leftArmPose.y
   override val rotateZ get() = leftArmPose.z
   
   override fun rotate(x: Float, y: Float, z: Float) {
      super.rotate(x, y, z)
      rotateRightArm(x, y, z)
   }
   
   override fun withItem(item: ItemStack) {
      itemInHand = item
   }
   
   override fun offset() = localization
   
   override fun click(player: Player, slot: Int) {
      if (slot == 0) for (clicker in clickers) clicker(player, slot)
   }
}

/**
 * Creates a new [ItemRotation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun itemRotation(location: Location, block: ItemRotation.() -> Unit): ItemRotation {
   return ItemRotation().apply {
      block()
      spawnInWorld(location, false)
      start()
   }
}

/**
 * Creates a new [ItemRotation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 *
 * @param stopAfter - Stops this animation after the given amount of seconds.
 */
inline fun itemRotation(location: Location, stopAfter: Int, block: ItemRotation.() -> Unit): ItemRotation {
   return ItemRotation().apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}
