package walkmc.animation

import net.minecraft.server.*
import net.minecraft.server.DamageSource
import net.minecraft.server.Entity
import net.minecraft.server.World
import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.util.*
import walkmc.*

/**
 * An abstract implementation of [StandAnimation].
 *
 * This works like a skeletal model for any stand animation.
 */
abstract class BaseStandAnimation(world: World?) : EntityArmorStand(world), StandAnimation {
   
   override val stand get() = entityBukkit
   override val entityStand get() = this
   
   override var removeOnStop = true
   
   override var isStarted = false
   override var isStopped = false
   override var delay = 1
   override var ticks = 0
   
   override var starters: StartSet = LinkedHashSet()
   override var stoppers: StopSet = LinkedHashSet()
   override var tickers: TickSet = LinkedHashSet()
   override var clickers: ClickSet = LinkedHashSet()
   
   init {
      setBasePlate(false)
      setGravity(false)
      isInvisible = true
      noclip = true
      boundingBox = NullBoundingBox()
   }
   
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
   
   override fun a(player: EntityHuman, vec3d: Vec3D): Boolean {
      val p = player.entityBukkit as Player
      val vec = Vector(vec3d.a, vec3d.b, vec3d.c)
      for (action in clickers) action(p, vec)
      return false
   }
   
   fun move(location: Location) {
      setPosition(location)
      broadcastTeleportPacket()
   }
   
   override fun teleportTo(exit: Location, portal: Boolean) {
      if (world.world != exit.world) {
         super.teleportTo(exit, portal)
      } else {
         setPosition(exit)
         broadcastTeleportPacket()
      }
   }
   
   override fun t_() {
      if (!isStarted || ticks++ % delay != 0) return
      tick()
   }
   
   override fun onDisable() = die()
   override fun receive(entity: Entity?, amount: Int) = Unit
   override fun damageEntity(damagesource: DamageSource?, f: Float) = false
   override fun ad() = false
   override fun burn(damage: Float) = Unit
   override fun burnFromLava() = Unit
   override fun isBurning() = false
   override fun collide(entity: Entity?) = Unit
   override fun onCollideWithPlayer(entityhuman: EntityHuman?) = Unit
   override fun e(tag: NBTTagCompound?) = Unit
   override fun f(tag: NBTTagCompound?) = Unit
   override fun d(nbttagcompound: NBTTagCompound?) = false
   override fun c(nbttagcompound: NBTTagCompound?) = false
   override fun ae() = false
   override fun makeSound(sound: String?, volume: Float, pitch: Float) = Unit
   override fun playSound(sound: String?, volume: Float, pitch: Float) = Unit
   override fun b(tag: NBTTagCompound?) = Unit
   override fun a(tag: NBTTagCompound?) = Unit
}
