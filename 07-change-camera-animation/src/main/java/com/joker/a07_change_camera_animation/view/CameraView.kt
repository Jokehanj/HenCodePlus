package com.joker.a07_change_camera_animation.view

import android.content.Context
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

    private val WIDTH = ScreenUtils.dp2Px(150f)
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mCamera = Camera()

    private val mAvatarBitmap: Bitmap = BitmapUtils.getAvatar(
        WIDTH.toInt(),
        getContext(),
        R.drawable.icon_avatar
    )

    init {
        mCamera.rotateX(30f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()

        canvas.translate((width / 2).toFloat(), (height / 2).toFloat())
        mCamera.applyToCanvas(canvas)
        canvas.translate(-WIDTH / 2, -WIDTH / 2)

        canvas.clipRect(0f, WIDTH / 2, WIDTH, WIDTH)
        canvas.drawBitmap(mAvatarBitmap, 0f, 0f, mPaint)
        canvas.restore()

    }
}