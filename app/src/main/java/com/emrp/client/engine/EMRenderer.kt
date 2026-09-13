package com.emrp.client.engine

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class EMRenderer(private val game: EMGameState) : GLSurfaceView.Renderer {

    private val camera = EMCamera()
    private val cube = EMCube()
    private val ground = EMGround()
    private val character = EMCharacter()

    private val projection = FloatArray(16)
    private val model = FloatArray(16)
    private val mvp = FloatArray(16)

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.32f, 0.55f, 0.82f, 1f)
        GLES20.glEnable(GLES20.GL_DEPTH_TEST)
        cube.initialize()
        ground.initialize()
    }

    override fun onSurfaceChanged(
        gl: GL10?,
        width: Int,
        height: Int
    ) {
        GLES20.glViewport(0, 0, width, height)

        val ratio = width.toFloat() / height.toFloat()

        Matrix.frustumM(
            projection,
            0,
            -ratio,
            ratio,
            -1f,
            1f,
            0.1f,
            100f
        )
    }

    override fun onDrawFrame(gl: GL10?) {
        game.update()

        GLES20.glClear(
            GLES20.GL_COLOR_BUFFER_BIT or
                GLES20.GL_DEPTH_BUFFER_BIT
        )

        val view = camera.getViewMatrix(game.player)

        Matrix.setIdentityM(model, 0)

        // Ground
        Matrix.setIdentityM(model, 0)

        Matrix.multiplyMM(mvp, 0, view, 0, model, 0)
        Matrix.multiplyMM(mvp, 0, projection, 0, mvp, 0)

        ground.draw(mvp)

        // Player character
        Matrix.setIdentityM(model, 0)

        Matrix.multiplyMM(mvp, 0, view, 0, model, 0)
        Matrix.multiplyMM(mvp, 0, projection, 0, mvp, 0)

        character.draw(
            cube,
            mvp,
            game.player.x,
            game.player.y,
            game.player.z
        )

    }
}
