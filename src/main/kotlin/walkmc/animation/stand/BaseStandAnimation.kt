package walkmc.animation.stand

import net.minecraft.server.*
import net.minecraft.server.DamageSource
import net.minecraft.server.Entity
import org.bukkit.*
import org.bukkit.entity.*
import walkmc.*
import walkmc.animation.*
import walkmc.extensions.collections.*
import java.util.concurrent.*

/**
 * An abstract implementation of [StandAnimation].
 *
 * This works like a skeletal model for any stand animation.
 */
abstract class BaseStandAnimation : EntityArmorStand(null), StandAnimation {
   
   override val entityStand get() = entityBukkit
   override val stand get() = this
   
   override var removeOnStop = true
   
   override var isStarted = false
   override var isStopped = false
   override val ticks get() = ticksLived
   
   override var starters: StartSet = LinkedHashSet()
   override var stoppers: StopSet = LinkedHashSet()
   override var tickers: TickSet = newSetFromMap(ConcurrentHashMap())
   override var clickers: ClickSet = LinkedHashSet()
   override var colliders: CollideSet = LinkedHashSet()
   
   init {
      setBasePlate(true)
      setGravity(false)
      isInvisible = true
      boundingBox = NullBoundingBox()
   }
   
   abstract fun click(player: Player, slot: Int)
   abstract fun offset(): Location
   
   override fun tick() {
      for (action in tickers) action()
   }
   
   override fun start() {
      if (isStarted) return
      for (action in starters) action()
      isStopped = false
      isStarted = true
   }
   
   override fun stop() {
      if (!isStarted) return
      for (action in stoppers) action()
      isStarted = false
      isStopped = true
      if (removeOnStop) die()
   }
   
   override fun a(entityhuman: EntityHuman, i: Int) {
      click(entityhuman.entityBukkit as Player, i)
   }
   
   override fun a(player: EntityHuman, vec3d: Vec3D): Boolean {
      super.a(player, vec3d)
      return false
   }
   
   override fun teleportTo(exit: Location, portal: Boolean) {
      if (world.world != exit.world) {
         super.teleportTo(exit, portal)
      } else {
         setPositionRotation(exit)
         broadcastTeleportPacket()
      }
   }
   
   override fun t_() {
      if (!isStarted) return
      tick()
   }
   
   override fun onCollideWithPlayer(entityhuman: EntityHuman) {
      if (!entityhuman.isNear(offset(), 0.85)) return
      val p = entityhuman.entityBukkit as Player
      for (collider in colliders) collider(p)
   }
   
   /*
   override fun collide(entity: Entity?) {
      if (entity !is EntityHuman) return
      val p = entity.entityBukkit as Player
      for (collider in colliders) collider(p)
   }
   
    */
   
   override fun onDisable() = die()
   override fun receive(entity: Entity?, amount: Int) = Unit
   override fun damageEntity(damagesource: DamageSource?, f: Float) = false
   override fun ad() = false
   override fun burn(damage: Float) = Unit
   override fun burnFromLava() = Unit
   override fun isBurning() = false
   //override fun onCollideWithPlayer(entityhuman: EntityHuman?) = Unit
   override fun e(tag: NBTTagCompound?) = Unit
   override fun f(tag: NBTTagCompound?) = Unit
   override fun d(nbttagcompound: NBTTagCompound?) = false
   override fun c(nbttagcompound: NBTTagCompound?) = false
   override fun ae() = false
   override fun makeSound(sound: String?, volume: Float, pitch: Float) = Unit
   override fun playSound(sound: String?, volume: Float, pitch: Float) = Unit
   override fun b(tag: NBTTagCompound?) = Unit
   override fun a(tag: NBTTagCompound?) = Unit
   override fun move(x: Double, y: Double, z: Double) = Unit
}
