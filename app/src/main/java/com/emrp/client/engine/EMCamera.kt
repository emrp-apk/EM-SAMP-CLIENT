package com.emrp.client.engine

import android.opengl.Matrix
import kotlin.math.cos
import kotlin.math.sin

class EMCamera {

    private val viewMatrix = FloatArray(16)

    fun getViewMatrix(player: EMPlayer): FloatArray {

        val yawRad = Math.toRadians(player.rotation.toDouble())

        val cameraDistance = 6f
        val cameraHeight = 3f

        val cameraX =
            player.x - sin(yawRad).toFloat() * cameraDistance

        val cameraZ =
            player.z + cos(yawRad).toFloat() * cameraDistance

        Matrix.setLookAtM(
            viewMatrix,
            0,
            cameraX,
            player.y + cameraHeight,
            cameraZ,
            player.x,
            player.y + 1f,
            player.z,
            0f,
            1f,
            0f
        )

        return viewMatrix
    }
}
