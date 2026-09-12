package com.emrp.client.engine

class EMPlayer(
    var id: Int = 0,
    var x: Float = 0f,
    var y: Float = 0f,
    var z: Float = 0f
) {
    var rotation: Float = 0f
    var health: Float = 100f
    var moving: Boolean = false

    fun move(dx: Float, dy: Float, dz: Float) {
        x += dx
        y += dy
        z += dz
        moving = true
    }

    fun stop() {
        moving = false
    }
}
