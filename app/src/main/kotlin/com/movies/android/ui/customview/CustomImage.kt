package com.movies.android.ui.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.movies.android.R
import com.movies.android.common.extension.getCompatColor
import com.movies.android.common.extension.runIfNotNull

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class CustomImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var withBorder = false

    init {
        attrs.runIfNotNull {
            getContext().obtainStyledAttributes(attrs, R.styleable.CustomImage).apply {
                withBorder = getBoolean(R.styleable.CustomImage_withBorder, withBorder)
                recycle()
            }
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (withBorder) {
            val strokeWidth = 5
            val paint = Paint()
            val rect = Rect(
                strokeWidth / 2,
                strokeWidth / 2,
                width - strokeWidth / 2,
                height - strokeWidth / 2
            )
            paint.style = Paint.Style.STROKE
            paint.color = context.getCompatColor(R.color.border)
            paint.strokeWidth = strokeWidth.toFloat()
            canvas.drawRect(rect, paint)
        }
    }

    object ImageBinding {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl"])
        fun setUrl(imageView: ImageView, imageUrl: String?) {
            imageUrl?.let {
                if (it.isNotEmpty()) {
                    Glide.with(imageView.context)
                        .load(it)
                        .into(imageView)
                } else {
                    imageView.setImageDrawable(null)
                    imageView.setOnClickListener(null)
                }
            }
        }
    }
}