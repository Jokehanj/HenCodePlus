package com.joker.a06_drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.joker.commend_unils.ScreenUtils
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by joke on 2020/9/8 .
 */
class PieChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private val RADIUS = ScreenUtils.dp2Px(150f)
        private val MOVE_SIZE = ScreenUtils.dp2Px(20f)
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBundles: RectF = RectF()

    private val rangeList = mutableListOf<Float>()
    private val colorList = mutableListOf<Int>()

    private var mCurrentRange = 0f

    private val mMoveIndex = 3

    init {

        rangeList.add(50f)
        rangeList.add(40f)
        rangeList.add(80f)
        rangeList.add(70f)
        rangeList.add(110f)
        rangeList.add(10f)

        colorList.add(Color.RED)
        colorList.add(Color.BLUE)
        colorList.add(Color.GREEN)
        colorList.add(Color.YELLOW)
        colorList.add(Color.LTGRAY)
        colorList.add(Color.MAGENTA)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mBundles.set(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (index in 0 until rangeList.size) {

            mPaint.color = colorList[index]

            canvas.save()

            if (index == mMoveIndex) {
                canvas.translate(
                    (cos(Math.toRadians((mCurrentRange + rangeList[index] / 2).toDouble())) * MOVE_SIZE).toFloat(),
                    (sin(Math.toRadians((mCurrentRange + rangeList[index] / 2).toDouble())) * MOVE_SIZE).toFloat()
                )
            }

            canvas.drawArc(mBundles, mCurrentRange, rangeList[index], true, mPaint)
            canvas.restore()
            mCurrentRange += rangeList[index]
        }
    }
}