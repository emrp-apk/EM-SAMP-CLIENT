package com.emrp.client.engine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class EMControlsView(
    context: Context,
    private val input: EMInput
) : View(context) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.argb(110, 255, 255, 255)
        paint.style = Paint.Style.FILL

        canvas.drawCircle(120f, height - 120f, 70f, paint)

        paint.color = Color.WHITE
        paint.textSize = 32f
        canvas.drawText("↑", 108f, height - 110f, paint)
        canvas.drawText("←", 65f, height - 70f, paint)
        canvas.drawText("→", 145f, height - 70f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        if (event.action == MotionEvent.ACTION_DOWN ||
            event.action == MotionEvent.ACTION_MOVE) {

            input.reset()

            if (x < 200f && y > height - 200f) {
                if (y < height - 130f) {
                    input.forward = true
                } else if (x < 100f) {
                    input.left = true
                } else {
                    input.right = true
                }
            }

            invalidate()
            return true
        }

        if (event.action == MotionEvent.ACTION_UP) {
            input.reset()
            invalidate()
            return true
        }

        return true
    }
}
