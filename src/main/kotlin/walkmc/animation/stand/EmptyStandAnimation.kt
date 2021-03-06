package walkmc.animation.stand

import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.inventory.*
import walkmc.animation.*
import walkmc.animation.extensions.*
import walkmc.extensions.*

/**
 * An empty stand animation. This doesn't have any base-animation.
 */
open class EmptyStandAnimation : BaseStandAnimation() {
   override fun withItem(item: ItemStack) = Unit
   
   override fun click(player: Player, slot: Int) {
      for (clicker in clickers) clicker(player, slot)
   }
   
   override fun offset() = localization up headHeight + 0.15
}

/**
 * Creates a new [EmptyStandAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 */
inline fun emptyAnimation(location: Location, block: EmptyStandAnimation.() -> Unit): EmptyStandAnimation {
   return EmptyStandAnimation().apply {
      setLocation(location)
      block()
      spawn()
      start()
   }
}

/**
 * Creates a new [EmptyStandAnimation] animation in the given [location] applying [block].
 *
 * ### Note: the animation will be automatically started.
 *
 * @param stopAfter - Stops this animation after the given amount of seconds.
 */
inline fun emptyAnimation(
   location: Location,
   stopAfter: Int,
   block: EmptyStandAnimation.() -> Unit
): EmptyStandAnimation {
   return EmptyStandAnimation().apply {
      setLocation(location)
      block()
      stopAfter(stopAfter * 20)
      spawn()
      start()
   }
}
