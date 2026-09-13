package com.emrp.client.engine

import kotlin.math.atan2

class EMPlayerController(
    private val player: EMPlayer,
    private val input: EMInput
) {

    private val speed = 0.08f

    fun update() {
        var dx = 0f
        var dz = 0f

        if (input.forward) dz -= speed
        if (input.backward) dz += speed
        if (input.left) dx -= speed
        if (input.right) dx += speed

        if (dx != 0f || dz != 0f) {
            player.move(dx, 0f, dz)

            player.rotation =
                Math.toDegrees(atan2(dx.toDouble(), -dz.toDouble())).toFloat()
        } else {
            player.stop()
        }
    }
}
