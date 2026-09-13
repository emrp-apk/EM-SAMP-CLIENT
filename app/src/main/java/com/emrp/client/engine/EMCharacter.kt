package com.emrp.client.engine

import android.opengl.Matrix

class EMCharacter {

    private val parts = listOf(
        Part(0f, 1.8f, 0f, 0.35f, 0.35f, 0.35f), // head
        Part(0f, 1.0f, 0f, 0.45f, 0.65f, 0.25f), // body
        Part(-0.6f, 1.0f, 0f, 0.15f, 0.6f, 0.15f), // left arm
        Part(0.6f, 1.0f, 0f, 0.15f, 0.6f, 0.15f), // right arm
        Part(-0.22f, 0.25f, 0f, 0.18f, 0.65f, 0.18f), // left leg
        Part(0.22f, 0.25f, 0f, 0.18f, 0.65f, 0.18f) // right leg
    )

    data class Part(
        val x: Float,
        val y: Float,
        val z: Float,
        val sx: Float,
        val sy: Float,
        val sz: Float
    )

    fun draw(
        cube: EMCube,
        mvp: FloatArray,
        playerX: Float,
        playerY: Float,
        playerZ: Float
    ) {
        val model = FloatArray(16)
        val localMvp = FloatArray(16)

        for (part in parts) {
            Matrix.setIdentityM(model, 0)

            Matrix.translateM(
                model, 0,
                playerX + part.x,
                playerY - 1f + part.y,
                playerZ + part.z
            )

            Matrix.scaleM(
                model, 0,
                part.sx,
                part.sy,
                part.sz
            )

            Matrix.multiplyMM(
                localMvp, 0,
                mvp, 0,
                model, 0
            )

            cube.draw(localMvp)
        }
    }
}
