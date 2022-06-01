package com.movies.android.ui.customview.page

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import com.movies.android.R
import com.movies.android.common.extension.*
import com.movies.android.ui.customview.CustomButton
import com.movies.android.ui.customview.CustomText

/**
 * Created by Berk Bayraktar on 26.04.2022
 */

class CustomPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var layoutContent: View? = null
    private var layoutLoading: View? = null
    private var layoutError: View? = null

    @LayoutRes
    private var layoutLoadingRes: Int = R.layout.cl_loading

    @LayoutRes
    private var layoutErrorRes: Int = R.layout.cl_error

    private var state: State = State.CONTENT

    private var animLoading: Animation? = null
    private var animProgress: Animation? = null

    private var listenerError: OnCustomPageErrorListener? = null

    enum class State {
        CONTENT, LOADING, ERROR, NONE
    }

    init {
        if (isInEditMode) state = State.CONTENT
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomPage, 0, 0)
            .apply {
                state = State.values()[getInteger(
                    R.styleable.CustomPage_sl_state,
                    State.CONTENT.ordinal
                )]
                layoutLoadingRes = getResourceId(
                    R.styleable.CustomPage_sl_loading,
                    R.layout.cl_loading
                )
                layoutErrorRes = getResourceId(
                    R.styleable.CustomPage_sl_error,
                    R.layout.cl_error
                )
                recycle()
            }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupContentState()
        setupLoadingState()
        setupErrorState()
        updateWithState()
        checkChildCount()
    }

    private fun setupContentState() {
        layoutContent = getChildAt(0)
        layoutContent?.visibility = View.GONE
    }

    private fun setupLoadingState() {
        layoutLoading = inflateLayout(layoutLoadingRes)
        layoutLoading?.visibility = View.GONE
        addView(layoutLoading)
    }

    private fun setupErrorState() {
        layoutError = inflateLayout(layoutErrorRes)
        layoutError?.visibility = View.GONE
        addView(layoutError)
    }

    private fun updateWithState() {
        when (state) {
            State.LOADING -> loading()
            State.CONTENT -> content()
            State.ERROR -> error()
            State.NONE -> hideAll()
        }
    }

    private fun checkChildCount() {
        if (childCount > 3 || childCount == 0) {
            throwChildCountException()
        }
    }

    private fun hideAll() {
        updateLoadingVisibility(View.GONE)
        layoutContent.gone()
        layoutError.gone()
    }

    private fun updateLoadingVisibility(visibility: Int) =
        when (visibility) {
            View.VISIBLE -> layoutLoading.visible {
                it.startViewAnimation(
                    R.id.customView_state_layout_loading,
                    animLoading
                )
            }
            else -> layoutLoading.gone { it.clearViewAnimation(R.id.customView_state_layout_loading) }
        }

    private fun throwChildCountException(): Nothing =
        throw IllegalStateException("CustomPage can host only one direct child")

    private fun errorMessage(message: String): CustomPage {
        if (message.isNotEmpty()) layoutError.findView<CustomText>(R.id.tvMessage) {
            text = message
            visibility = View.VISIBLE
        }
        return this
    }

    private fun errorButton(buttonText: String): CustomPage {
        if (buttonText.isNotEmpty()) layoutError.findView<CustomButton>(R.id.btAction) {
            text = buttonText
            visibility = View.VISIBLE
            setOnClickListener { listenerError?.onCustomPageErrorClick() }
        }
        return this
    }

    fun content(): CustomPage {
        state = State.CONTENT
        updateLoadingVisibility(View.GONE)
        layoutError.gone()
        layoutContent.visible()
        return this
    }

    fun loading(): CustomPage {
        state = State.LOADING
        layoutContent.gone()
        layoutError.gone()
        updateLoadingVisibility(View.VISIBLE)
        return this
    }

    fun error(): CustomPage {
        state = State.ERROR
        updateLoadingVisibility(View.GONE)
        layoutContent.gone()
        layoutError.visible()
        return this
    }

    fun showState(stateInfo: StateInfo?) {
        animLoading = stateInfo?.animLoading
        animProgress = stateInfo?.animProgress
        when (stateInfo?.state) {
            State.CONTENT -> content()
            State.LOADING -> loading()
            State.ERROR -> loadingError(stateInfo)
            null, State.NONE -> hideAll()
        }
    }

    private fun loadingError(stateInfo: StateInfo?) {
        error()
        errorMessage(context.getString(R.string.err))
        errorButton(context.getString(R.string.bt_retry))
    }

    fun setPageErrorListener(listener: OnCustomPageErrorListener): CustomPage {
        listenerError = listener
        return this
    }

    interface OnCustomPageErrorListener {
        fun onCustomPageErrorClick()
    }

    data class StateInfo(
        val throwable: Throwable? = null,
        val infoImage: Int? = null,
        val infoTitle: String? = null,
        val infoMessage: String? = null,
        val infoCode: Int? = null,
        val infoButtonText: String? = null,
        val state: State? = null,
        val onInfoButtonClick: (() -> Unit)? = null,
        val onDialogPositiveClick: (() -> Unit)? = null,
        val onDialogNegativeClick: (() -> Unit)? = null,
        val animLoading: Animation? = null,
        val animProgress: Animation? = null
    )

    object PageBinding {

        @JvmStatic
        @BindingAdapter("state", "error", requireAll = false)
        fun showState(view: CustomPage, state: State?, error: Throwable? = null) {
            view.showState(
                StateInfo(
                    state = state ?: State.NONE,
                    throwable = error
                )
            )
        }
    }
}

