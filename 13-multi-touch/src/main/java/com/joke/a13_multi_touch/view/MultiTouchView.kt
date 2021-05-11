package com.joke.a13_multi_touch.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.joke.a13_multi_touch.R
import com.joker.commend_unils.BitmapUtils
import com.joker.commend_unils.ScreenUtils

/**
 * Created by hanji on 5/8/21 .
 * Desc:
 */
class MultiTouchView constructor(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val WIDTH = ScreenUtils.dp2Px(200f)
    private val mAvatarBitmap: Bitmap =
        BitmapUtils.getAvatar(WIDTH.toInt(), context, R.drawable.icon_avatar)

    private var mTouchX = 0f
    private var mTouchY = 0f

    private var mOffsetX = 0f
    private var mOffsetY = 0f

    private var mOriginalX = 0f
    private var mOriginalY = 0f

    private var mPointIndexId = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {

                mPointIndexId = event.getPointerId(0)

                mOffsetX = event.x
                mOffsetY = event.y

                mOriginalX = mTouchX
                mOriginalY = mTouchY
            }
            MotionEvent.ACTION_MOVE -> {

                val pointIndex = event.findPointerIndex(mPointIndexId)

                mTouchX = mOriginalX + event.getX(pointIndex) - mOffsetX
                mTouchY = mOriginalY + event.getY(pointIndex) - mOffsetY

                invalidate()
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val pointIndex = event.actionIndex

                mPointIndexId = event.getPointerId(pointIndex)

                mOffsetX = event.getX(pointIndex)
                mOffsetY = event.getY(pointIndex)

                mOriginalX = mTouchX
                mOriginalY = mTouchY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex

                val actionIndexId = event.getPointerId(actionIndex)

                if (actionIndexId == mPointIndexId) {

                    val pointIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }

                    mPointIndexId = event.getPointerId(pointIndex)

                    mOffsetX = event.getX(pointIndex)
                    mOffsetY = event.getY(pointIndex)

                    mOriginalX = mTouchX
                    mOriginalY = mTouchY
                }
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(mAvatarBitmap, mTouchX, mTouchY, mPaint)
    }
}