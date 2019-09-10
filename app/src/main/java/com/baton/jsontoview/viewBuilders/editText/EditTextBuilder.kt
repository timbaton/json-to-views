package com.baton.jsontoview.viewBuilders.editText

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.utils.pxToDp
import com.baton.jsontoview.viewBuilders.IViewBuilder
import com.baton.jsontoview.viewBuilders.factories.ViewPropertiesFactory
import com.jakewharton.rxbinding.widget.RxTextView
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

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
) : IViewBuilder {

    private var mView: EditText? = null

    private val marginTop = 4
    private val marginBottom = 4
    private val marginStart = 16
    private val marginEnd = 16


    override fun create(): View {

        mView = EditText(context)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        layoutParams.setMargins(marginStart.pxToDp(context), marginTop.pxToDp(context), marginEnd.pxToDp(context), marginBottom.pxToDp(context))
        mView!!.layoutParams = layoutParams

        mView!!.hint = element.view!!.prompt

        setOnInputDataChanged()

        return getView()
    }

    override fun setOnInputDataChanged() {
        RxTextView.textChanges(mView as TextView)
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                val isValid = element.validator?.let {
                    viewPropertiesFactory.editTextProperty!!.isValid(text.toString(), it.predicate.pattern)
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