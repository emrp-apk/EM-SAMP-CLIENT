package com.emrp.client.engine

import android.content.Context
import android.opengl.GLSurfaceView

class EMSampGameView(
    context: Context,
    val gameState: EMGameState
) : GLSurfaceView(context) {

    init {
        setEGLContextClientVersion(2)
        setRenderer(EMRenderer(gameState))
        renderMode = RENDERMODE_CONTINUOUSLY
    }
}
