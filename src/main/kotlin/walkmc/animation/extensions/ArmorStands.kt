package walkmc.animation.extensions

import net.minecraft.server.*
import org.bukkit.*
import org.bukkit.inventory.ItemStack
import walkmc.extensions.*

/**
 * Updates the client with the given equipment [item] in [slot].
 */
fun EntityArmorStand.updateItem(item: net.minecraft.server.ItemStack?, slot: Int) {
   if (worldServer != null)
      worldServer.getTracker().a(this, PacketPlayOutEntityEquipment(getId(), slot, item))
}

/**
 * Sets the item equipment in the given [slot] and updates to the client.
 */
fun EntityArmorStand.setItem(item: net.minecraft.server.ItemStack?, slot: Int) {
   items[slot] = item
   updateItem(item, slot)
}

/**
 * Sets the item equipment in the given [slot] and updates to the client.
 */
fun EntityArmorStand.setItem(item: ItemStack?, slot: Int) = setItem(item?.handlerCopy(), slot)

/**
 * Gets the hand equipment of this armor stand.
 */
inline var EntityArmorStand.itemInHand: ItemStack
   get() = items[0].cachedCraftItem
   set(value) = setItem(value, 0)

/**
 * Gets the boots equipment of this armor stand.
 */
inline var EntityArmorStand.boots: ItemStack
   get() = items[1].cachedCraftItem
   set(value) = setItem(value, 0)

/**
 * Gets the legs equipment of this armor stand.
 */
inline var EntityArmorStand.legs: ItemStack
   get() = items[2].cachedCraftItem
   set(value) = setItem(value, 2)

/**
 * Gets the chestplate equipment of this armor stand.
 */
inline var EntityArmorStand.chestplate: ItemStack
   get() = items[3].cachedCraftItem
   set(value) = setItem(value, 3)

/**
 * Gets the head equipment of this armor stand.
 */
inline var EntityArmorStand.head: ItemStack
   get() = items[4].cachedCraftItem
   set(value) = setItem(value, 4)

//
//   Heads
//

fun EntityArmorStand.rotateHead(x: Float, y: Float, z: Float) = setHeadPose(headPose.add(x, y, z))
fun EntityArmorStand.rotateHead(x: Double, y: Double, z: Double) = setHeadPose(headPose.add(x, y, z))
fun EntityArmorStand.rotateHead(x: Int, y: Int, z: Int) = setHeadPose(headPose.add(x, y, z))

fun EntityArmorStand.rotateHeadX(x: Float) = setHeadPose(headPose.addX(x))
fun EntityArmorStand.rotateHeadX(x: Double) = setHeadPose(headPose.addX(x))
fun EntityArmorStand.rotateHeadX(x: Int) = setHeadPose(headPose.addX(x))

fun EntityArmorStand.rotateHeadY(y: Float) = setHeadPose(headPose.addY(y))
fun EntityArmorStand.rotateHeadY(y: Double) = setHeadPose(headPose.addY(y))
fun EntityArmorStand.rotateHeadY(y: Int) = setHeadPose(headPose.addY(y))

fun EntityArmorStand.rotateHeadZ(z: Float) = setHeadPose(headPose.addZ(z))
fun EntityArmorStand.rotateHeadZ(z: Double) = setHeadPose(headPose.addZ(z))
fun EntityArmorStand.rotateHeadZ(z: Int) = setHeadPose(headPose.addZ(z))

//
//   Left Arms
//

fun EntityArmorStand.rotateLeftArm(x: Float, y: Float, z: Float) = setLeftArmPose(leftArmPose.add(x, y, z))
fun EntityArmorStand.rotateLeftArm(x: Double, y: Double, z: Double) = setLeftArmPose(leftArmPose.add(x, y, z))
fun EntityArmorStand.rotateLeftArm(x: Int, y: Int, z: Int) = setLeftArmPose(leftArmPose.add(x, y, z))

fun EntityArmorStand.rotateLeftArmX(x: Float) = setLeftArmPose(leftArmPose.addX(x))
fun EntityArmorStand.rotateLeftArmX(x: Double) = setLeftArmPose(leftArmPose.addX(x))
fun EntityArmorStand.rotateLeftArmX(x: Int) = setLeftArmPose(leftArmPose.addX(x))

fun EntityArmorStand.rotateLeftArmY(y: Float) = setLeftArmPose(leftArmPose.addY(y))
fun EntityArmorStand.rotateLeftArmY(y: Double) = setLeftArmPose(leftArmPose.addY(y))
fun EntityArmorStand.rotateLeftArmY(y: Int) = setLeftArmPose(leftArmPose.addY(y))

fun EntityArmorStand.rotateLeftArmZ(z: Float) = setLeftArmPose(leftArmPose.addZ(z))
fun EntityArmorStand.rotateLeftArmZ(z: Double) = setLeftArmPose(leftArmPose.addZ(z))
fun EntityArmorStand.rotateLeftArmZ(z: Int) = setLeftArmPose(leftArmPose.addZ(z))

//
//   Right Arms
//

fun EntityArmorStand.rotateRightArm(x: Float, y: Float, z: Float) = setRightArmPose(rightArmPose.add(x, y, z))
fun EntityArmorStand.rotateRightArm(x: Double, y: Double, z: Double) = setRightArmPose(rightArmPose.add(x, y, z))
fun EntityArmorStand.rotateRightArm(x: Int, y: Int, z: Int) = setRightArmPose(rightArmPose.add(x, y, z))

fun EntityArmorStand.rotateRightArmX(x: Float) = setRightArmPose(rightArmPose.addX(x))
fun EntityArmorStand.rotateRightArmX(x: Double) = setRightArmPose(rightArmPose.addX(x))
fun EntityArmorStand.rotateRightArmX(x: Int) = setRightArmPose(rightArmPose.addX(x))

fun EntityArmorStand.rotateRightArmY(y: Float) = setRightArmPose(rightArmPose.addY(y))
fun EntityArmorStand.rotateRightArmY(y: Double) = setRightArmPose(rightArmPose.addY(y))
fun EntityArmorStand.rotateRightArmY(y: Int) = setRightArmPose(rightArmPose.addY(y))

fun EntityArmorStand.rotateRightArmZ(z: Float) = setRightArmPose(rightArmPose.addZ(z))
fun EntityArmorStand.rotateRightArmZ(z: Double) = setRightArmPose(rightArmPose.addZ(z))
fun EntityArmorStand.rotateRightArmZ(z: Int) = setRightArmPose(rightArmPose.addZ(z))

//
//   Left Legs
//

fun EntityArmorStand.rotateLeftLeg(x: Float, y: Float, z: Float) = setLeftLegPose(leftLegPose.add(x, y, z))
fun EntityArmorStand.rotateLeftLeg(x: Double, y: Double, z: Double) = setLeftLegPose(leftLegPose.add(x, y, z))
fun EntityArmorStand.rotateLeftLeg(x: Int, y: Int, z: Int) = setLeftLegPose(leftLegPose.add(x, y, z))

fun EntityArmorStand.rotateLeftLegX(x: Float) = setLeftLegPose(leftLegPose.addX(x))
fun EntityArmorStand.rotateLeftLegX(x: Double) = setLeftLegPose(leftLegPose.addX(x))
fun EntityArmorStand.rotateLeftLegX(x: Int) = setLeftLegPose(leftLegPose.addX(x))

fun EntityArmorStand.rotateLeftLegY(y: Float) = setLeftLegPose(leftLegPose.addY(y))
fun EntityArmorStand.rotateLeftLegY(y: Double) = setLeftLegPose(leftLegPose.addY(y))
fun EntityArmorStand.rotateLeftLegY(y: Int) = setLeftLegPose(leftLegPose.addY(y))

fun EntityArmorStand.rotateLeftLegZ(z: Float) = setLeftLegPose(leftLegPose.addZ(z))
fun EntityArmorStand.rotateLeftLegZ(z: Double) = setLeftLegPose(leftLegPose.addZ(z))
fun EntityArmorStand.rotateLeftLegZ(z: Int) = setLeftLegPose(leftLegPose.addZ(z))

//
//   Right Legs
//

fun EntityArmorStand.rotateRightLeg(x: Float, y: Float, z: Float) = setRightLegPose(rightLegPose.add(x, y, z))
fun EntityArmorStand.rotateRightLeg(x: Double, y: Double, z: Double) = setRightLegPose(rightLegPose.add(x, y, z))
fun EntityArmorStand.rotateRightLeg(x: Int, y: Int, z: Int) = setRightLegPose(rightLegPose.add(x, y, z))

fun EntityArmorStand.rotateRightLegX(x: Float) = setRightLegPose(rightLegPose.addX(x))
fun EntityArmorStand.rotateRightLegX(x: Double) = setRightLegPose(rightLegPose.addX(x))
fun EntityArmorStand.rotateRightLegX(x: Int) = setRightLegPose(rightLegPose.addX(x))

fun EntityArmorStand.rotateRightLegY(y: Float) = setRightLegPose(rightLegPose.addY(y))
fun EntityArmorStand.rotateRightLegY(y: Double) = setRightLegPose(rightLegPose.addY(y))
fun EntityArmorStand.rotateRightLegY(y: Int) = setRightLegPose(rightLegPose.addY(y))

fun EntityArmorStand.rotateRightLegZ(z: Float) = setRightLegPose(rightLegPose.addZ(z))
fun EntityArmorStand.rotateRightLegZ(z: Double) = setRightLegPose(rightLegPose.addZ(z))
fun EntityArmorStand.rotateRightLegZ(z: Int) = setRightLegPose(rightLegPose.addZ(z))

//
//   Body
//

fun EntityArmorStand.rotateBody(x: Float, y: Float, z: Float) = setBodyPose(bodyPose.add(x, y, z))
fun EntityArmorStand.rotateBody(x: Double, y: Double, z: Double) = setBodyPose(bodyPose.add(x, y, z))
fun EntityArmorStand.rotateBody(x: Int, y: Int, z: Int) = setBodyPose(bodyPose.add(x, y, z))

fun EntityArmorStand.rotateBodyX(x: Float) = setBodyPose(bodyPose.addX(x))
fun EntityArmorStand.rotateBodyX(x: Double) = setBodyPose(bodyPose.addX(x))
fun EntityArmorStand.rotateBodyX(x: Int) = setBodyPose(bodyPose.addX(x))

fun EntityArmorStand.rotateBodyY(y: Float) = setBodyPose(bodyPose.addY(y))
fun EntityArmorStand.rotateBodyY(y: Double) = setBodyPose(bodyPose.addY(y))
fun EntityArmorStand.rotateBodyY(y: Int) = setBodyPose(bodyPose.addY(y))

fun EntityArmorStand.rotateBodyZ(z: Float) = setBodyPose(bodyPose.addZ(z))
fun EntityArmorStand.rotateBodyZ(z: Double) = setBodyPose(bodyPose.addZ(z))
fun EntityArmorStand.rotateBodyZ(z: Int) = setBodyPose(bodyPose.addZ(z))

/**
 * Moves this armor stand to the specified [location].
 */
fun Entity.move(location: Location) {
   setPositionRotation(location)
   broadcastTeleportPacket()
}

fun Entity.moveUpper(distance: Float) = move(localization up distance)
fun Entity.moveUpper(distance: Double) = move(localization up distance)
fun Entity.moveUpper(distance: Int) = move(localization up distance)

fun Entity.moveDown(distance: Float) = move(localization down distance)
fun Entity.moveDown(distance: Double) = move(localization down distance)
fun Entity.moveDown(distance: Int) = move(localization down distance)

fun Entity.moveNorth(distance: Float) = move(localization north distance)
fun Entity.moveNorth(distance: Double) = move(localization north distance)
fun Entity.moveNorth(distance: Int) = move(localization north distance)

fun Entity.moveSouth(distance: Float) = move(localization south distance)
fun Entity.moveSouth(distance: Double) = move(localization south distance)
fun Entity.moveSouth(distance: Int) = move(localization south distance)

fun Entity.moveWest(distance: Float) = move(localization west distance)
fun Entity.moveWest(distance: Double) = move(localization west distance)
fun Entity.moveWest(distance: Int) = move(localization west distance)

fun Entity.moveEast(distance: Float) = move(localization east distance)
fun Entity.moveEast(distance: Double) = move(localization east distance)
fun Entity.moveEast(distance: Int) = move(localization east distance)

fun Entity.setLocation(location: Location) {
   world = location.world.handler
   setPositionRotation(location)
}

fun Entity.spawn(force: Boolean = false) {
   world.addEntity(this, force)
}
