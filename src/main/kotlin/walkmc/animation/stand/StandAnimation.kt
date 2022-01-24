package walkmc.animation.stand

import net.minecraft.server.*
import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import walkmc.*
import walkmc.animation.*
import walkmc.extensions.*

typealias ClickSet = MutableSet<ClickAction>
typealias ClickAction = Player.(Int) -> Unit

typealias CollideSet = MutableSet<CollideAction>
typealias CollideAction = Player.() -> Unit

/**
 * Represents an animation for Armor Stands.
 */
interface StandAnimation : Animation {
   
   /**
    * The current location of this stand animation.
    */
   val location: Location get() = stand.localization
   
   /**
    * If the stand will be removed after [stop] call.
    */
   var removeOnStop: Boolean
   
   /**
    * All clicker listener to be executed.
    */
   var clickers: ClickSet
   
   /**
    * All collide listener to be executed.
    */
   var colliders: CollideSet
   
   /**
    * The bukkit entity owning this stand animation.
    */
   val entityStand: ArmorStand
   
   /**
    * The NMS entity owning this animation.
    */
   val stand: EntityArmorStand
   
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
   
   /**
    * Add a collide listener to be executed.
    */
   fun onCollide(action: CollideAction) {
      colliders += action
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
