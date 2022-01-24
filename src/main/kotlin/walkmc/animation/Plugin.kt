package walkmc.animation

import walkmc.*
import walkmc.Plugin
import walkmc.animation.impl.*
import walkmc.animation.sub.*
import walkmc.extensions.*
import walkmc.instructor.*

object Plugin : Plugin() {
   override fun start() {
      
      commandOf("mining") {
         miningAnimation(player.location.north(5), int(0)) {
            withItem(player.itemInHand)
            force = float(1)
            onCollide { player.giveExpLevels(1) }
         }
      }
      
      commandOf("rotate") {
         headRotation(player.location.north(5), int(0)) {
            withItem(player.itemInHand)
            force(float(1), float(2), float(3))
            onCollide { stop() }
         }
      }
      
      commandOf("cycle") {
         moveableAnimation(player.location.north(5), int(0)) {
            allowReverseOrder = true
            offsetX = double(1)
            offsetY = double(2)
            offsetZ = double(3)
            withItem(player.itemInHand)
            
            onCollide { player.giveItem(newItem(Materials.IRON_INGOT)) }
            
            cycle(20) {
               addCycle(Materials.LIME_GLASS)
               addCycle(Materials.RED_GLASS)
               addCycle(Materials.BLUE_GLASS)
            }
            
            onTick(2) { Particle.LAVA.play(location up 1.8) }
         }
      }
      
   }
}
