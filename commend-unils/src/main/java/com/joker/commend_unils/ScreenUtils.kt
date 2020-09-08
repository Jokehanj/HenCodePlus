package com.joker.commend_unils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by joke on 2020/9/8 .
 */
class ScreenUtils {

    companion object {
        public fun dp2Px(dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().displayMetrics
            )
        }
    }
}