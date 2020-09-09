package com.joker.a06_drawing.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.joker.a06_drawing.R
import com.joker.commend_unils.BitmapUtils
import com.joker.commend_unils.ScreenUtils

/**
 * Created by joke on 2020/9/8 .
 */
class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mAvatarBitmap: Bitmap = BitmapUtils.getAvatar(
        ScreenUtils.dp2Px(200f).toInt(),
        getContext(),
        R.drawable.icon_avatar
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        canvas.drawBitmap(mAvatarBitmap, ScreenUtils.dp2Px(50f), ScreenUtils.dp2Px(50f), mPaint)

    }
}