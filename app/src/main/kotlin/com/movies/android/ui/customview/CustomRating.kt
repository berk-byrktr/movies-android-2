package com.movies.android.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.databinding.BindingAdapter
import com.movies.android.R
import com.movies.android.common.extension.getCompatColor
import kotlin.math.min

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class CustomRating @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress: Int = 50
        set(value) {
            field = if (progress <= 100) value else 100
            invalidate()
        }

    var progressColor: Int = R.color.white
        set(value) {
            field = value
            invalidate()
        }

    private val paintStroke = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = context.getCompatColor(progressColor)
        strokeCap = Paint.Cap.ROUND
        strokeWidth = resources.getDimension(R.dimen.dim_rating_stroke_height)
    }

    private val paintTextProgress = Paint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        color = context.getCompatColor(R.color.white)
        textSize = resources.getDimension(R.dimen.fnt_small)
        typeface = Typeface.createFromAsset(context.assets, "fonts/HelveticaNeue_Bold.otf")
    }

    private val paintTextPercentage = Paint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        color = context.getCompatColor(R.color.white)
        textSize = resources.getDimension(R.dimen.fnt_super_small)
        typeface = Typeface.createFromAsset(context.assets, "fonts/HelveticaNeue.otf")
    }

    private var rectF = RectF()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = min(width, height)
        setMeasuredDimension(min, min)
        val strokeHeight = resources.getDimension(R.dimen.dim_rating_stroke_height)
        rectF.set(
            0 + strokeHeight / 2,
            0 + strokeHeight / 2,
            min - strokeHeight / 2,
            min - strokeHeight / 2
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(rectF, paintStroke.apply {
            color = context.getCompatColor(progressColor)
            alpha = 50
        })
        canvas.drawArc(rectF, 270f, (360 * progress / 100).toFloat(), false, paintStroke.apply {
            color = context.getCompatColor(progressColor)
            alpha = 255
        })
        canvas.drawText(
            progress.toString(),
            (width / 2).toFloat(),
            height / 2 - (paintTextProgress.descent() + paintTextProgress.ascent()) / 2,
            paintTextProgress
        )
        canvas.drawText(
            "%",
            width / 2 - (paintTextProgress.descent() + paintTextProgress.ascent()),
            height / 2 + (paintTextPercentage.descent() + paintTextPercentage.ascent()) / 2,
            paintTextPercentage
        )
    }

    object RatingBinding {
        @JvmStatic
        @BindingAdapter(value = ["progress"])
        fun setProgress(rating: CustomRating, progress: Int?) {
            rating.progress = progress ?: 0
        }

        @JvmStatic
        @BindingAdapter(value = ["progressColor"])
        fun setProgressColor(rating: CustomRating, progressColor: Int?) {
            rating.progressColor = progressColor ?: R.color.white
        }
    }
}
