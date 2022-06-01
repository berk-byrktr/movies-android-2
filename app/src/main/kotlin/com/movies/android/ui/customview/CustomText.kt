package com.movies.android.ui.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.movies.android.R
import com.movies.android.common.extension.runIfNotNull

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class CustomText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    enum class FontType {
        FONT_REGULAR, FONT_BOLD
    }

    private var fontType: FontType = FontType.FONT_REGULAR

    init {
        attrs.runIfNotNull {
            context.obtainStyledAttributes(attrs, R.styleable.CustomText, 0, 0).apply {
                fontType = FontType.values()[getInteger(
                    R.styleable.CustomText_fontType, FontType.FONT_REGULAR.ordinal
                )]
                typeface =
                    Typeface.createFromAsset(context.assets, "fonts/" + getFontName(fontType))
                recycle()
            }
        }
    }

    private fun getFontName(fontType: FontType): String {
        return when (fontType) {
            FontType.FONT_REGULAR -> "HelveticaNeue.otf"
            FontType.FONT_BOLD -> "HelveticaNeue_Bold.otf"
        }
    }
}