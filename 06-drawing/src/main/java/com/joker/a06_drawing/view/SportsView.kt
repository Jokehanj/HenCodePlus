package com.joker.a06_drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.joker.commend_unils.ScreenUtils

/**
 * Created by hanji on 2020/9/10 .
 */
class SportsView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val RADIUS = ScreenUtils.dp2Px(100f)

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontMetrics: Paint.FontMetrics

    init {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = ScreenUtils.dp2Px(20f)

        fontMetrics = mPaint.fontMetrics
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = Color.GRAY
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), RADIUS, mPaint)

        mPaint.color = Color.RED
        mPaint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            -100f,
            200f,
            false,
            mPaint
        )

        mPaint.textAlign = Paint.Align.CENTER
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = ScreenUtils.dp2Px(20f)

        canvas.drawText(
            "Hello World！",
            (width / 2).toFloat(), (height - fontMetrics.ascent - fontMetrics.descent) / 2,
            mPaint
        )
    }
}