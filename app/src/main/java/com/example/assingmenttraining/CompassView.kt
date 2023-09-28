package com.example.assingmenttraining

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CompassView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var position: Float = 0f

    init {
        paint.strokeWidth = 5f
        paint.textSize = 30f
        paint.style = Paint.Style.STROKE
    }

    fun updatePosition(position: Float) {
        this.position = position
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val canvasWidth = width
        val canvasHeight = height
        val radius = min(canvasWidth, canvasHeight) / 2.0f

        // Draw background
        paint.color = Color.GRAY
        canvas.drawCircle((canvasWidth / 2).toFloat(), (canvasHeight / 2).toFloat(), radius, paint)

        // Draw needles
        canvas.save()
        canvas.rotate(position, (canvasWidth / 2).toFloat(), (canvasHeight / 2).toFloat())

        paint.color = Color.RED
        canvas.drawLine(
            (canvasWidth / 2).toFloat(),
            (canvasHeight / 2).toFloat(),
            (canvasWidth / 2).toFloat(),
            (canvasHeight / 2 - radius),
            paint
        )

        paint.color = Color.BLUE
        canvas.drawLine(
            (canvasWidth / 2).toFloat(),
            (canvasHeight / 2).toFloat(),
            (canvasWidth / 2).toFloat(),
            (canvasHeight / 2 + radius),
            paint
        )

        canvas.restore()
    }
}
