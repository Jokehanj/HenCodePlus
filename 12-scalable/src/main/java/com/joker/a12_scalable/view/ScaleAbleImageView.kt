package com.joker.a12_scalable.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.joker.a12_scalable.R
import com.joker.commend_unils.BitmapUtils
import com.joker.commend_unils.ScreenUtils

/**
 * Created by hanji on 4/21/21 .
 * Desc:
 */
class ScaleAbleImageView constructor(context: Context, attrs: AttributeSet?) :
    View(context, attrs), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener,
    Runnable {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val WIDTH = ScreenUtils.dp2Px(300f)
    private val SCALEABLE = 2.0f
    private val mAvatarBitmap: Bitmap =
        BitmapUtils.getAvatar(WIDTH.toInt(), context, R.drawable.icon_avatar)

    private var mOffsetX = 0f
    private var mOffsetY = 0f
    private var mScaleAbleOffsetX = 0f
    private var mScaleAbleOffsetY = 0f

    private var mSmallScale = 1f
    private var mBigScale = 1f

    private var isBig = false

    var scaleFunction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val objectAnimation = ObjectAnimator.ofFloat(this, "scaleFunction", 0f, 1f)

    private val mGestureDetector: GestureDetectorCompat = GestureDetectorCompat(context, this)

    private val overScroller = OverScroller(context)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mScaleAbleOffsetX = (width - mAvatarBitmap.width) / 2f
        mScaleAbleOffsetY = (height - mAvatarBitmap.height) / 2f

        if (mAvatarBitmap.width / mAvatarBitmap.height > width / height) {
            // 图片更宽
            mSmallScale = width.toFloat() / mAvatarBitmap.width
            mBigScale = height.toFloat() / mAvatarBitmap.height * SCALEABLE
        } else {
            mSmallScale = height.toFloat() / mAvatarBitmap.height
            mBigScale = width.toFloat() / mAvatarBitmap.width * SCALEABLE
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scale = mSmallScale + (mBigScale - mSmallScale) * scaleFunction

        if (!isBig) {
            mOffsetX *= scaleFunction
            mOffsetY *= scaleFunction
        }

        canvas.translate(mOffsetX, mOffsetY)

        canvas.scale(scale, scale, width / 2f, height / 2f)

        canvas.drawBitmap(mAvatarBitmap, mScaleAbleOffsetX, mScaleAbleOffsetY, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        // 接收down事件，需要返回true
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
        // 显示按压效果，回调会存在延迟。类似于touch中isInScrollingContainer效果
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        // 单击
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        // 滑动事件，move；e1为起始位置，e2为当前位置

        if (isBig) {

            mOffsetX -= distanceX

            mOffsetX = mOffsetX.coerceAtMost((mAvatarBitmap.width * mBigScale - width) / 2)
            mOffsetX = mOffsetX.coerceAtLeast(-(mAvatarBitmap.width * mBigScale - width) / 2)

            mOffsetY -= distanceY

            mOffsetY = mOffsetY.coerceAtMost((mAvatarBitmap.height * mBigScale - height) / 2)
            mOffsetY = mOffsetY.coerceAtLeast(-(mAvatarBitmap.height * mBigScale - height) / 2)

            invalidate()
        }
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        // 长按事件
        return
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        // 惯性滚动 e1为起始位置，e2为当前位置

        if (isBig) {
            overScroller.fling(
                mOffsetX.toInt(),
                mOffsetY.toInt(),
                velocityX.toInt(),
                velocityY.toInt(),
                (-(mAvatarBitmap.width * mBigScale - width) / 2).toInt(),
                ((mAvatarBitmap.width * mBigScale - width) / 2).toInt(),
                (-(mAvatarBitmap.height * mBigScale - height) / 2).toInt(),
                ((mAvatarBitmap.height * mBigScale - height) / 2).toInt()
            )
            postOnAnimation(this)
        }

        return false
    }

    private fun refreshScalePoint() {
        if (overScroller.computeScrollOffset()) {
            mOffsetX = overScroller.currX.toFloat()
            mOffsetY = overScroller.currY.toFloat()
            invalidate()
            postOnAnimation(this)
        }
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        // 区分于onSingleTapUp，如果监听了双击，需要单击使用该函数。双击鉴别失败的点击会调用，有一定的延迟
        return false
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        // 双击
        isBig = !isBig

        if (isBig) {
            objectAnimation.start()
        } else {
            objectAnimation.reverse()
        }

        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        // 双击之后不松手，之后的滑动事件回调
        return false
    }

    override fun run() {
        refreshScalePoint()
    }
}