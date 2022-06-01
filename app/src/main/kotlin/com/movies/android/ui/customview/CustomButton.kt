package com.movies.android.ui.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.movies.android.R
import com.movies.android.common.extension.runIfNotNull
import com.movies.android.databinding.CvButtonBinding

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: CvButtonBinding =
        CvButtonBinding.inflate(LayoutInflater.from(context), this)

    var text: String = ""
        set(value) {
            field = value
            binding.tv.text = text
        }

    var textColor: Int = R.color.textBlack
        set(value) {
            field = value
            binding.tv.setTextColor(ContextCompat.getColor(context, textColor))
        }

    var fontType: Int = Typeface.NORMAL
        set(value) {
            field = value
            binding.tv.setTypeface(binding.tv.typeface, fontType)
        }

    init {
        attrs.runIfNotNull {
            getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton).apply {
                getString(R.styleable.CustomButton_text)?.let { text = it }
                textColor = getResourceId(R.styleable.CustomButton_textColor, textColor)
                fontType = getInt(R.styleable.CustomButton_fontType, fontType)
                recycle()
            }
        }
    }
}