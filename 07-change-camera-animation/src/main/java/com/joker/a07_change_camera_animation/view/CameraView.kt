package com.joker.a07_change_camera_animation.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.joker.a07_change_camera_animation.R
import com.joker.commend_unils.BitmapUtils
import com.joker.commend_unils.ScreenUtils

/**
 * Created by hanji on 2020/9/10 .
 */
class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val MARGIN = ScreenUtils.dp2Px(100f)
    private val WIDTH = ScreenUtils.dp2Px(200f)
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mCamera = Camera()

    var topRotateX = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomRotateX = 0f
        set(value) {
            field = value
            invalidate()
        }
    var rotate = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val mAvatarBitmap: Bitmap = BitmapUtils.getAvatar(
        WIDTH.toInt(),
        getContext(),
        R.drawable.icon_avatar
    )

    init {
        mCamera.setLocation(0f, 0f, getZForCamera())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制顶部
        canvas.save()
        canvas.translate(MARGIN + WIDTH / 2, MARGIN + WIDTH / 2)
        canvas.rotate(-rotate)

        mCamera.save()
        mCamera.rotateX(topRotateX)
        mCamera.applyToCanvas(canvas)
        mCamera.restore()

        canvas.clipRect(-WIDTH, -WIDTH, WIDTH, 0f)
        canvas.rotate(rotate)
        canvas.translate(-(MARGIN + WIDTH / 2), -(MARGIN + WIDTH / 2))
        canvas.drawBitmap(mAvatarBitmap, MARGIN, MARGIN, mPaint)
        canvas.restore()

        // 绘制底部
        canvas.save()
        canvas.translate(MARGIN + WIDTH / 2, MARGIN + WIDTH / 2)
        canvas.rotate(-rotate)

        mCamera.save()
        mCamera.rotateX(bottomRotateX)
        mCamera.applyToCanvas(canvas)
        mCamera.restore()

        canvas.clipRect(-WIDTH, 0f, WIDTH, WIDTH)
        canvas.rotate(rotate)
        canvas.translate(-(MARGIN + WIDTH / 2), -(MARGIN + WIDTH / 2))
        canvas.drawBitmap(mAvatarBitmap, MARGIN, MARGIN, mPaint)
        canvas.restore()
    }

    private fun getZForCamera(): Float {
        return -6 * Resources.getSystem().displayMetrics.density
    }
}