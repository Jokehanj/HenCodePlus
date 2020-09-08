package com.joker.a06_drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.joker.commend_unils.ScreenUtils
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by joke on 2020/9/8 .
 */
class DashBoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private val WIDTH = ScreenUtils.dp2Px(300f)
        private val HEIGHT = ScreenUtils.dp2Px(200f)

        private val LENGTH = ScreenUtils.dp2Px(80f)

        private const val ANGLE = 120f
    }

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mDash: Path = Path()

    private var mPathDashPathEffect: PathDashPathEffect

    init {
        // 设置paint属性
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = ScreenUtils.dp2Px(2f)

        // 设置dash的绘制区域
        mDash.addRect(0f, 0f, ScreenUtils.dp2Px(2f), ScreenUtils.dp2Px(10f), Path.Direction.CW)


        val path = Path()
        path.addArc(
            width / 2 - WIDTH / 2,
            height / 2 - HEIGHT / 2,
            width / 2 + WIDTH / 2,
            height / 2 + HEIGHT / 2,
            (90 + ANGLE / 2),
            (360 - ANGLE)
        )

        val pathMeasure = PathMeasure(path, false)

        mPathDashPathEffect = PathDashPathEffect(
            mDash,
            (pathMeasure.length - ScreenUtils.dp2Px(2f)) / 20, 0f, PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(
            width / 2 - WIDTH / 2,
            height / 2 - HEIGHT / 2,
            width / 2 + WIDTH / 2,
            height / 2 + HEIGHT / 2,
            (90 + ANGLE / 2),
            (360 - ANGLE),
            false,
            mPaint
        )
        mPaint.pathEffect = mPathDashPathEffect

        canvas.drawArc(
            width / 2 - WIDTH / 2,
            height / 2 - HEIGHT / 2,
            width / 2 + WIDTH / 2,
            height / 2 + HEIGHT / 2,
            (90 + ANGLE / 2),
            (360 - ANGLE),
            false,
            mPaint
        )
        mPaint.pathEffect = null

        // 指针线
        mPaint.color = Color.RED
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (cos(Math.toRadians(getAngleForMark(4).toDouble())) * LENGTH).toFloat() + width / 2,
            (sin(Math.toRadians(getAngleForMark(4).toDouble())) * LENGTH).toFloat() + height / 2,
            mPaint
        )

        mPaint.color = Color.BLACK
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (cos(Math.toRadians(getAngleForMark(12).toDouble())) * LENGTH).toFloat() + width / 2,
            (sin(Math.toRadians(getAngleForMark(12).toDouble())) * LENGTH).toFloat() + height / 2,
            mPaint
        )
    }

    private fun getAngleForMark(mark: Int): Int {
        return (90 + ANGLE / 2 + (360 - ANGLE) / 20 * mark).toInt()
    }
}