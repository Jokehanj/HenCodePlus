package com.joker.a08_animation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import com.joker.commend_unils.ScreenUtils

/**
 * Created by joke on 2020/9/12 .
 */
class PointView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var point = Point()
        set(value) {
            field = value
            invalidate()
        }

    init {
        mPaint.strokeWidth = ScreenUtils.dp2Px(20f)
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPoint(point.x.toFloat(), point.y.toFloat(), mPaint)
    }
}