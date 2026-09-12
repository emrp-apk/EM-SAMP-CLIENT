package com.emrp.client.engine

import android.opengl.Matrix

class EMCamera {

    val position = floatArrayOf(0f, 1.7f, 6f)

    var yaw = 0f
    var pitch = 0f

    private val viewMatrix = FloatArray(16)

    fun getViewMatrix(): FloatArray {
        val lookX = kotlin.math.sin(Math.toRadians(yaw.toDouble())).toFloat()
        val lookZ = kotlin.math.cos(Math.toRadians(yaw.toDouble())).toFloat()

        Matrix.setLookAtM(
            viewMatrix,
            0,
            position[0],
            position[1],
            position[2],
            position[0] + lookX,
            position[1] + kotlin.math.sin(Math.toRadians(pitch.toDouble())).toFloat(),
            position[2] - lookZ,
            0f,
            1f,
            0f
        )

        return viewMatrix
    }
}
