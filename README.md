<a href="https://github.com/uinnn/Animation-Framework">
  <img align="center" src="https://img.shields.io/static/v1?style=for-the-badge&label=author&message=uinnn&color=informational"/>
</a>
<a href="https://github.com/uinnn/Animation-Framework">
  <img align="center" src="https://img.shields.io/static/v1?style=for-the-badge&label=version&message=1.0.0&color=yellow"/>
</a>

# animation-framework

### Note:
> This repository is a showcase from WalkMC Network animation-framework.
> This will not work in your server.

> The performance is much better than creating an vanilla ArmorStand and scheduling a bukkit task

## Armor stand animations:
* HeadRotation
* ItemRotation
* MiningAnimation
* MoveableAnimation
* TeleportAnimation

## Supports:
* Start animation listeners
* Stop animation listeners
* Tick listeners
* Click listeners
* Collide listeners

## Showcase:
```kt
// creates a mining animation that's stops after 10 seconds
miningAnimation(location, stopAfter = 10) {
  customName = "I give experience!"
  withItem(item) // sets the item displaying
  force = 3f // sets animation force
  onCollide { giveExpLevels(1) } // add a collide listener that's adds 1 level of xp
}
```

```kt
// creates a head rotation without an end.
headRotation(location) {
  withItem(item)
  force(x = 2f, y = 3f, z = 2f) // sets XYZ animation force
  onCollide { stop() } // stops animation after colliding
  onClick { log("You clicked me!") } // add click listener to send a message to the player
}
```

```kt
// creates a moveable rotation without an end.
// this example will create a animation that's will go upper and down
moveableAnimation(location) {
  allowReverseOrder = true
  offsetY = 0.08f // sets the force of offsetY
  withItem(item)
  onTick(2) { Particle.LAVA.play(location up 1.8) } // add tick listener to show lava particle every 2 tick
}
```

## Sub Animations
* ItemCycle
* ParticlePath
* HelixPath
* DampenedWavesPath
* TornadoPath

## Showcase:
```kt
emptyAnimation(location) {
  // add ItemCycle sub animation changing the item displayed every 20 ticks
  cycle(20) {
    addCycle(Materials.STONE)
    addCycle(Materials.GRASS)
  }
}
```

```kt
emptyAnimation(location) {
  // add ParticlePath to display Flames particle in a circle of 1.0 radius and 10 points
  particlePath(Particle.FLAME, location.drawCirclePath(radius = 1.0, amount = 10))
}
```

```kt
emptyAnimation(location) {
  // add helix path sub animation
  helix(Particle.FLAME) {
    allowReverseOrder = true
  }
  
  // simplest
  helix(Particle.FLAME)
}
```

```kt
emptyAnimation(location) {
  // add helix path sub animation
  dampenedWaves(Particle.FLAME) {
    repeat = true
  }
  
  // simplest
  dampenedWaves(Particle.FLAME)
}
```

```kt
emptyAnimation(location) {
  // add tornado path sub animation
  tornado(Particle.FLAME) {
    maxHeight = 20.0
    lines = 6
  }
  
  // simplest
  tornado(Particle.FLAME)
}
```







