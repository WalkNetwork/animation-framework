package walkmc.animation.extensions

import net.minecraft.server.*

fun Vector3f.add(x: Float, y: Float, z: Float) = Vector3f(this.x + x, this.y + y, this.z + z)
fun Vector3f.add(x: Double, y: Double, z: Double) = add(x.toFloat(), y.toFloat(), z.toFloat())
fun Vector3f.add(x: Int, y: Int, z: Int) = Vector3f(this.x + x, this.y + y, this.z + z)

fun Vector3f.addX(x: Float) = Vector3f(this.x + x, y, z)
fun Vector3f.addX(x: Double) = add(x.toFloat(), y, z)
fun Vector3f.addX(x: Int) = Vector3f(this.x + x, y, z)

fun Vector3f.addY(y: Float) = Vector3f(x, this.y + y, z)
fun Vector3f.addY(y: Double) = add(x, y.toFloat(), z)
fun Vector3f.addY(y: Int) = Vector3f(x, this.y + y, z)

fun Vector3f.addZ(z: Float) = Vector3f(x, y, this.z + z)
fun Vector3f.addZ(z: Double) = add(x, y, z.toFloat())
fun Vector3f.addZ(z: Int) = Vector3f(x, y, this.z + z)
