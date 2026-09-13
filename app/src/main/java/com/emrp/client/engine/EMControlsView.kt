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

    private val buttonSize = 90f
    private val gap = 12f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.argb(120, 255, 255, 255)
        paint.style = Paint.Style.FILL

        val left = 70f
        val bottom = height - 70f

        // Up
        canvas.drawRect(
            left + buttonSize + gap,
            bottom - buttonSize * 2 - gap,
            left + buttonSize * 2 + gap,
            bottom - buttonSize - gap,
            paint
        )

        // Left
        canvas.drawRect(
            left,
            bottom - buttonSize,
            left + buttonSize,
            bottom,
            paint
        )

        // Down
        canvas.drawRect(
            left + buttonSize + gap,
            bottom - buttonSize,
            left + buttonSize * 2 + gap,
            bottom,
            paint
        )

        // Right
        canvas.drawRect(
            left + buttonSize * 2 + gap * 2,
            bottom - buttonSize,
            left + buttonSize * 3 + gap * 2,
            bottom,
            paint
        )

        paint.color = Color.WHITE
        paint.textSize = 42f

        canvas.drawText("↑", left + 125f, bottom - 105f, paint)
        canvas.drawText("←", left + 25f, bottom - 25f, paint)
        canvas.drawText("↓", left + 125f, bottom - 25f, paint)
        canvas.drawText("→", left + 225f, bottom - 25f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_UP ||
            event.action == MotionEvent.ACTION_CANCEL) {
            input.reset()
            invalidate()
            return true
        }

        if (event.action == MotionEvent.ACTION_DOWN ||
            event.action == MotionEvent.ACTION_MOVE) {

            input.reset()

            val x = event.x
            val y = event.y

            val left = 70f
            val bottom = height - 70f

            val upLeft = left + buttonSize + gap
            val upTop = bottom - buttonSize * 2 - gap

            if (x >= upLeft &&
                x <= upLeft + buttonSize &&
                y >= upTop &&
                y <= upTop + buttonSize) {

                input.forward = true

            } else if (
                x >= left &&
                x <= left + buttonSize &&
                y >= bottom - buttonSize &&
                y <= bottom
            ) {

                input.left = true

            } else if (
                x >= upLeft &&
                x <= upLeft + buttonSize &&
                y >= bottom - buttonSize &&
                y <= bottom
            ) {

                input.backward = true

            } else if (
                x >= left + buttonSize * 2 + gap * 2 &&
                x <= left + buttonSize * 3 + gap * 2 &&
                y >= bottom - buttonSize &&
                y <= bottom
            ) {

                input.right = true
            }

            invalidate()
            return true
        }

        return true
    }
}
