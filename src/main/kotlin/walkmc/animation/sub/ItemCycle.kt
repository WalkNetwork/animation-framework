package walkmc.animation.sub

import org.bukkit.inventory.*
import walkmc.*
import walkmc.animation.*
import walkmc.animation.interfaces.*
import walkmc.animation.stand.*
import walkmc.collections.*
import walkmc.extensions.*

/**
 * Represents an item cyle sub animation for [StandAnimation].
 */
open class ItemCycle : SubAnimation<StandAnimation> {
   var items = IndexList<ItemStack>()
   
   override fun animate(animation: StandAnimation, ticker: Tick) {
      animation.withItem(items.toNextOrFirst())
   }
   
   fun addCycle(item: ItemStack) = items.add(item)
   fun addCycle(material: Materials) = addCycle(material.toItem())
   fun addCycle(url: String) = addCycle(newHead(url))
   fun addCycleOwner(owner: String) = addCycle(newHeadOwner(owner))
}

/**
 * Adds a new [ItemCycle] sub animation applying the given [block].
 */
inline fun StandAnimation.cycle(block: ItemCycle.() -> Unit): ItemCycle {
   return addSub(ItemCycle().apply(block).cast())
}

/**
 * Adds a new [ItemCycle] sub animation applying the given [block]
 * with an interval of [ticks].
 */
inline fun StandAnimation.cycle(ticks: Int, block: ItemCycle.() -> Unit): ItemCycle {
   return addSub(ticks, ItemCycle().apply(block).cast())
}
