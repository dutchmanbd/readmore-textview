package com.zxdmjr.readmoretextview.internal

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import com.zxdmjr.readmoretextview.ReadMoreTextView




fun ReadMoreTextView.setEllipsizedSuffix(
    maxLines: Int, suffix: String,
    @ColorInt clickTextColor: Int,
    listener: (String) -> Unit
) {
    addOnLayoutChangeListener(object: View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom:     Int) {

            val allText = text.toString()
            var newText = allText
            val tvWidth = width
            val textSize = textSize

            if(!TextUtil.textHasEllipsized(newText, tvWidth, textSize, maxLines)) return

            while (TextUtil.textHasEllipsized(newText, tvWidth, textSize, maxLines)) {
                newText = newText.substring(0, newText.length - 1).trim()
            }

            val characterInSingleLine = (newText.length / (maxLines * 4))-1
            //now replace the last few chars with the suffix if we can
            val endIndex = newText.length - suffix.length - characterInSingleLine  //minus 1 just to make sure we have enough room
            if(endIndex > 0) {
                newText = "${newText.substring(0, endIndex).trim()} $suffix"
            }

            val spannable = SpannableString(newText)

            spannable.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    listener(allText)
                }
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false

                }
            }, endIndex, newText.length, 0)

//            FF4081
//            Color.parseColor("#C51162")
            spannable.setSpan(
                ForegroundColorSpan(clickTextColor),
                endIndex, newText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


            text = spannable

            removeOnLayoutChangeListener(this)
        }
    })
}