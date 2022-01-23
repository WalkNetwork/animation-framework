package walkmc.animation

import net.minecraft.server.*
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import org.bukkit.util.*
import walkmc.*
import walkmc.extensions.*

typealias ClickSet = MutableSet<ClickAction>
typealias ClickAction = Player.(Vector) -> Unit

/**
 * Represents an animation for Armor Stands.
 */
interface StandAnimation : Animation {
   
   /**
    * If the stand will be removed after [stop] call.
    */
   var removeOnStop: Boolean
   
   /**
    * All clicker listener to be executed.
    */
   var clickers: ClickSet
   
   /**
    * The bukkit entity owning this stand animation.
    */
   val stand: ArmorStand
   
   /**
    * The NMS entity owning this animation.
    */
   val entityStand: EntityArmorStand
   
   /**
    * Sets the item displaying.
    */
   fun withItem(item: ItemStack)
   
   /**
    * Add a clicker listener to be executed.
    */
   fun onClick(action: ClickAction) {
      clickers += action
   }
}

/** Sets the material to be displayed. */
fun StandAnimation.withMaterial(material: Materials) = withItem(material.toItem())

/** Sets the head to be displayed. */
fun StandAnimation.withHead(url: String) = withItem(newHead(url))

/** Sets the head to be displayed. */
fun StandAnimation.withHeadOwner(name: String) = withItem(newHeadOwner(name))

/** Sets the head to be displayed. */
fun StandAnimation.withHeadOwner(player: Player) = withItem(newHeadOwner(player.name))
