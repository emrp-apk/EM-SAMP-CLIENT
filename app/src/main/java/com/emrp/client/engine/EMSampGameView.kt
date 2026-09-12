package com.emrp.client.engine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class EMSampGameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        holder.addCallback(this)
        paint.textSize = 48f
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawFrame()
    }

    override fun surfaceChanged(
        holder: SurfaceHolder,
        format: Int,
        width: Int,
        height: Int
    ) {
        drawFrame()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    private fun drawFrame() {
        val canvas: Canvas = holder.lockCanvas() ?: return

        try {
            canvas.drawColor(Color.rgb(7, 9, 13))

            paint.color = Color.WHITE
            canvas.drawText(
                "EM-SAMP",
                60f,
                100f,
                paint
            )

            paint.color = Color.rgb(25, 230, 161)
            paint.textSize = 28f
            canvas.drawText(
                "Game Engine Initializing...",
                60f,
                150f,
                paint
            )
        } finally {
            holder.unlockCanvasAndPost(canvas)
        }
    }
}
