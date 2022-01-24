package walkmc.animation.impl

import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.inventory.*
import walkmc.animation.*
import walkmc.animation.extensions.*
import walkmc.animation.stand.*
import walkmc.extensions.*

/**
 * Represents a stand head rotation animation.
 */
open class HeadRotation : BaseStandRotation() {
   
   override val rotateX get() = headPose.x
   override val rotateY get() = headPose.y
   override val rotateZ get() = headPose.z
   
   override fun rotate(x: Float, y: Float, z: Float) {
      super.rotate(x, y, z)
      rotateHead(x, y, z)
   }
   
   override fun withItem(item: ItemStack) {
      head = item
   }
   
   override fun click(player: Player, slot: Int) {
      if (slot == 4) for (clicker in clickers) clicker(player, slot)
   }
   
   override fun offset() = localization up headHeight + 0.15
}

/**
 * Creates a new [HeadRotation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun headRotation(location: Location, block: HeadRotation.() -> Unit): HeadRotation {
   return HeadRotation().apply {
      block()
      spawnInWorld(location, false)
      start()
   }
}

/**
 * Creates a new [HeadRotation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 *
 * @param stopAfter - Stops this animation after the given amount of seconds.
 */
inline fun headRotation(location: Location, stopAfter: Int, block: HeadRotation.() -> Unit): HeadRotation {
   return HeadRotation().apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}
