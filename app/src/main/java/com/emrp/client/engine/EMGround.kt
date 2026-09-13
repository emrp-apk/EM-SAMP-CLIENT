package com.emrp.client.engine

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class EMGround {

    private val vertices = floatArrayOf(
        -20f, 0f, -20f,
         20f, 0f, -20f,
         20f, 0f,  20f,

        -20f, 0f, -20f,
         20f, 0f,  20f,
        -20f, 0f,  20f
    )

    private val buffer: FloatBuffer =
        ByteBuffer.allocateDirect(vertices.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .apply {
                put(vertices)
                position(0)
            }

    private val vertexShader = """
        attribute vec4 aPosition;
        uniform mat4 uMVP;

        void main() {
            gl_Position = uMVP * aPosition;
        }
    """.trimIndent()

    private val fragmentShader = """
        precision mediump float;

        void main() {
            gl_FragColor = vec4(0.12, 0.18, 0.14, 1.0);
        }
    """.trimIndent()

    private var program = 0
    private var positionHandle = 0
    private var mvpHandle = 0

    fun initialize() {
        val vs = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER)
        GLES20.glShaderSource(vs, vertexShader)
        GLES20.glCompileShader(vs)

        val fs = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER)
        GLES20.glShaderSource(fs, fragmentShader)
        GLES20.glCompileShader(fs)

        program = GLES20.glCreateProgram()
        GLES20.glAttachShader(program, vs)
        GLES20.glAttachShader(program, fs)
        GLES20.glLinkProgram(program)

        positionHandle =
            GLES20.glGetAttribLocation(program, "aPosition")

        mvpHandle =
            GLES20.glGetUniformLocation(program, "uMVP")
    }

    fun draw(mvp: FloatArray) {
        GLES20.glUseProgram(program)

        buffer.position(0)

        GLES20.glEnableVertexAttribArray(positionHandle)

        GLES20.glVertexAttribPointer(
            positionHandle,
            3,
            GLES20.GL_FLOAT,
            false,
            12,
            buffer
        )

        GLES20.glUniformMatrix4fv(
            mvpHandle,
            1,
            false,
            mvp,
            0
        )

        GLES20.glDrawArrays(
            GLES20.GL_TRIANGLES,
            0,
            vertices.size / 3
        )

        GLES20.glDisableVertexAttribArray(positionHandle)
    }
}
