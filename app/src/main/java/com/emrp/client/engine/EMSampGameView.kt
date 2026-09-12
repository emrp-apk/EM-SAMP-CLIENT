package com.emrp.client.engine

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class EMSampGameView(context: Context) : GLSurfaceView(context) {

    init {
        setEGLContextClientVersion(2)
        setRenderer(EMRenderer())
        renderMode = RENDERMODE_CONTINUOUSLY
    }

    private class OldRenderer : Renderer {

        override fun onSurfaceCreated(
            gl: GL10?,
            config: EGLConfig?
        ) {
            GLES20.glClearColor(0.03f, 0.04f, 0.06f, 1f)
        }

        override fun onSurfaceChanged(
            gl: GL10?,
            width: Int,
            height: Int
        ) {
            GLES20.glViewport(0, 0, width, height)
        }

        override fun onDrawFrame(gl: GL10?) {
            GLES20.glClear(
                GLES20.GL_COLOR_BUFFER_BIT or
                GLES20.GL_DEPTH_BUFFER_BIT
            )
        }
    }
}
