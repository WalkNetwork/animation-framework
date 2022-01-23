package walkmc.animation.rotation

import net.minecraft.server.*
import net.minecraft.server.World
import org.bukkit.*
import org.bukkit.inventory.ItemStack
import walkmc.animation.*
import walkmc.extensions.*

/**
 * Represents a stand head rotation animation.
 */
open class HeadRotation(world: World?) : BaseStandRotation(world) {
   
   override val rotateX get() = headPose.x
   override val rotateY get() = headPose.y
   override val rotateZ get() = headPose.z
   
   override fun rotate(x: Float, y: Float, z: Float) {
      super.rotate(x, y, z)
      setHeadPose(Vector3f(rotateX + x, rotateY + y, rotateZ + z))
   }
   
   override fun withItem(item: ItemStack) {
      setEquipment(4, item.handlerCopy())
   }
}

/**
 * Creates a new [HeadRotation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun headRotation(location: Location, block: HeadRotation.() -> Unit): HeadRotation {
   return HeadRotation(null).apply {
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
   return HeadRotation(null).apply {
      block()
      stopAfter(stopAfter * 20)
      spawnInWorld(location, false)
      start()
   }
}
