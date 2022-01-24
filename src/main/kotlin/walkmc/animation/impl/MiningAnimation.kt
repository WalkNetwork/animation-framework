package walkmc.animation.impl

import net.minecraft.server.*
import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import walkmc.animation.*
import walkmc.animation.extensions.*
import walkmc.animation.stand.*

/**
 * Represents a stand mining animation.
 */
open class MiningAnimation : BaseStandAnimation() {
   
   var min = -90f
   var max = -45f
   var count = min
   var force = 1.5f
   var reverseOrder = false
   
   init {
      setRightArmPose(Vector3f(min, 0f, 0f))
   }
   
   override fun tick() {
      if (!reverseOrder) {
         if (rightArmPose.x > max) reverseOrder = true
         rotateRightArmX(force)
      } else {
         if (rightArmPose.x < min) reverseOrder = false
         rotateRightArmX(-force)
      }
      
      super.tick()
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
 * Creates a new [MiningAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun miningAnimation(location: Location, block: MiningAnimation.() -> Unit): MiningAnimation {
   return MiningAnimation().apply {
      block()
      spawnInWorld(location, false)
      start()
   }
}

/**
 * Creates a new [MiningAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 *
 * @param stopAfter - Stops this animation after the given amount of seconds.
 */
inline fun miningAnimation(
   location: Location,
   stopAfter: Int,
   block: MiningAnimation.() -> Unit
): MiningAnimation {
   return MiningAnimation().apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}
