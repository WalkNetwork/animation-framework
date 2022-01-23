package walkmc.animation.sub

import org.bukkit.inventory.*
import walkmc.*
import walkmc.animation.*
import walkmc.collections.*

/**
 * Represents an item cyle sub animation for [StandAnimation].
 */
open class ItemCycle : SubAnimation {
   var items = IndexList<ItemStack>()
   
   override fun animate(primary: Animation) {
      if (primary is StandAnimation) primary.withItem(items.toNextOrFirst())
   }
   
   fun addCycle(item: ItemStack) {
      items += item
   }
   
   fun addCycle(material: Materials) = addCycle(material.toItem())
}

/**
 * Adds a new [ItemCycle] sub animation applying the given [block].
 */
inline fun StandAnimation.cycle(block: ItemCycle.() -> Unit): ItemCycle {
   return addSub(ItemCycle().apply(block))
}

/**
 * Adds a new [ItemCycle] sub animation applying the given [block]
 * with an interval of [ticks].
 */
inline fun StandAnimation.cycle(ticks: Int, block: ItemCycle.() -> Unit): ItemCycle {
   return addSub(ticks, ItemCycle().apply(block))
}
