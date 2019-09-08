package com.baton.jsontoview.utils.viewBuilder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.utils.viewProperty.ViewPropertiesFactory
import com.jakewharton.rxbinding.widget.RxTextView

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class EditTextBuilder(
    private var element: Element,
    private var context: Context,
    private var viewPropertiesFactory: ViewPropertiesFactory
) : IMyView {

    private var mView: EditText? = null

    private val marginTop = 4
    private val marginBottom = 4
    private val marginStart = 16
    private val marginEnd = 16


    override fun create(): View {
        val scale = context.resources.displayMetrics.density;

        // convert from px to dp
        fun Int.pxToDp(): Int {
            return (this * scale + 0.5f).toInt()
        }

        mView = EditText(context)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(marginStart.pxToDp(), marginTop.pxToDp(), marginEnd.pxToDp(), marginBottom.pxToDp())
        mView!!.layoutParams = layoutParams

        mView!!.hint = element.view!!.prompt

        setOnInputDataChanged()

        return getView()
    }

    override fun setOnInputDataChanged() {
        RxTextView.textChanges(mView as TextView)
            .subscribe { text ->
                val isValid = element.validator?.let {
                    viewPropertiesFactory.editTextCommand?.isValid(text.toString(), it.predicate.pattern)
                }

                if (isValid != null && !isValid) {
                    element.validator?.message?.let { setError(it) }
                } else {
                    removeError()
                }
            }
    }

    private fun setError(message: String) {
        mView!!.error = message
    }

    private fun removeError() {
        mView!!.error = null;
    }

    override fun getView(): View {
        return mView!!
    }
}