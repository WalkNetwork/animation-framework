package walkmc.animation.rotation

import net.minecraft.server.*
import net.minecraft.server.World
import org.bukkit.*
import org.bukkit.inventory.ItemStack
import walkmc.animation.*
import walkmc.extensions.*

/**
 * Represents a stand item rotation animation.
 */
open class ItemRotation(world: World?) : BaseStandRotation(world) {
   
   override val rotateX get() = leftArmPose.x
   override val rotateY get() = leftArmPose.y
   override val rotateZ get() = leftArmPose.z
   
   override fun rotate(x: Float, y: Float, z: Float) {
      super.rotate(x, y, z)
      setLeftArmPose(Vector3f(rotateX + x, rotateY + y, rotateZ + z))
   }
   
   override fun withItem(item: ItemStack) {
      setEquipment(0, item.handlerCopy())
   }
}

/**
 * Creates a new [ItemRotation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun itemRotation(location: Location, block: ItemRotation.() -> Unit): ItemRotation {
   return ItemRotation(null).apply {
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
   return ItemRotation(null).apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}
