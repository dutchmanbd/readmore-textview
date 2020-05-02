package com.zxdmjr.readmoretextview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.zxdmjr.readmoretextview.internal.ReadMoreListener
import com.zxdmjr.readmoretextview.internal.setEllipsizedSuffix


class ReadMoreTextView(
    context: Context,
    private val attrs: AttributeSet?
) : AppCompatTextView(context, attrs) {

    private var maxLine: Int = 2
    @ColorInt private var colorClickableText = 0
    var listener: ReadMoreListener? = null
    init {
        initialize()
    }

    @SuppressLint("Recycle")
    private fun initialize() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReadMoreTextView)
        this.colorClickableText = typedArray.getColor(R.styleable.ReadMoreTextView_clickable_text_color,
            ContextCompat.getColor(context, R.color.defaultClickColor))
        maxLine = this.maxLines
        this.movementMethod = LinkMovementMethod()
        this.highlightColor = Color.TRANSPARENT
        this.setEllipsizedSuffix(maxLine, "...more", colorClickableText){
            Log.d("ReadMoreTextView", "text: $it")
            if(listener != null){
                listener?.onReadMore(it)
            } else{
                showFullText(it)
            }

        }
    }

    private fun showFullText(text: String){
        MaterialDialog(context).show {
            title(text="Details")
            message(text=text)
            positiveButton(text="OK") {
                dismiss()
            }
        }
    }



}