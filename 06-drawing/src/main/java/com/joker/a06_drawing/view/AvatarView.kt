package com.joker.a06_drawing.view

import android.content.Context
import android.graphics.*
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
    private val MARGIN = ScreenUtils.dp2Px(50f)
    private val WIDTH = ScreenUtils.dp2Px(200f)
    private val mXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val PADDING = ScreenUtils.dp2Px(10f)

    private val mRectF = RectF(MARGIN, MARGIN, MARGIN + WIDTH, MARGIN + WIDTH)

    private val mAvatarBitmap: Bitmap = BitmapUtils.getAvatar(
        WIDTH.toInt(),
        getContext(),
        R.drawable.icon_avatar
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawOval(
            mRectF.left - PADDING,
            mRectF.top - PADDING,
            mRectF.right + PADDING,
            mRectF.bottom + PADDING, mPaint
        )

        val saveLayer = canvas.saveLayer(mRectF, mPaint)
        canvas.drawOval(mRectF, mPaint)
        mPaint.xfermode = mXfermode
        canvas.drawBitmap(mAvatarBitmap, MARGIN, MARGIN, mPaint)
        canvas.restoreToCount(saveLayer)

    }
}