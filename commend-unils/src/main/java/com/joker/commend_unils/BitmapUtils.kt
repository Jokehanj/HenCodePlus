package com.joker.commend_unils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * Created by joke on 2020/9/8 .
 */
class BitmapUtils {

    companion object {
        fun getAvatar(width: Int, context: Context, drawableId: Int): Bitmap {

            val options = BitmapFactory.Options()

            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(context.resources, drawableId, options)

            options.inJustDecodeBounds = false
            options.inDensity = options.outWidth
            options.inTargetDensity = width

            return BitmapFactory.decodeResource(context.resources, drawableId, options)
        }
    }
}