package com.zxdmjr.readmoretextview.internal

import android.graphics.Paint

class TextUtil {

    companion object{
        @JvmStatic
        fun textHasEllipsized(text: String, tvWidth: Int, textSize: Float, maxLines: Int): Boolean {
            val paint = Paint()
            paint.textSize = textSize
            val size = paint.measureText(text).toInt()

            return size > tvWidth * maxLines

//            val paint = Paint()
//            paint.textSize = textSize
//            val size = paint.measureText(text).toInt()
//            val newLineChars = StringUtils.countMatches(text, "\n")
//
//            return size > tvWidth * maxLines || newLineChars >= maxLines
        }
    }
}