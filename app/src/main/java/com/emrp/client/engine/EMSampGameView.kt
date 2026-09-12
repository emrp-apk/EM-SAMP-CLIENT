package com.emrp.client.engine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class EMSampGameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var running = false
    private var renderThread: Thread? = null

    init {
        holder.addCallback(this)
        paint.textSize = 48f
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        running = true

        renderThread = Thread {
            while (running) {
                drawFrame()
                Thread.sleep(16)
            }
        }

        renderThread?.start()
    }

    override fun surfaceChanged(
        holder: SurfaceHolder,
        format: Int,
        width: Int,
        height: Int
    ) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        running = false
        renderThread?.join()
        renderThread = null
    }

    private fun drawFrame() {
        val canvas: Canvas = holder.lockCanvas() ?: return

        try {
            canvas.drawColor(Color.rgb(7, 9, 13))

            paint.textSize = 48f
            paint.color = Color.WHITE
            canvas.drawText("EM-SAMP", 60f, 100f, paint)

            paint.textSize = 28f
            paint.color = Color.rgb(25, 230, 161)
            canvas.drawText(
                "EM-SAMP ENGINE",
                60f,
                150f,
                paint
            )
        } finally {
            holder.unlockCanvasAndPost(canvas)
        }
    }
}
