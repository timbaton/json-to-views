package com.baton.jsontoview.utils.viewBuilder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.baton.jsontoview.data.entity.MyView
import com.baton.jsontoview.data.entity.Validator
import com.jakewharton.rxbinding.widget.RxTextView
import java.lang.NullPointerException

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class MyEditText : IMyView {

    private var mView: EditText? = null
    private var validator: Validator? = null

    private val marginTop = 4
    private val marginBottom = 4
    private val marginStart = 16
    private val marginEnd = 16


    override fun create(view: MyView, context: Context) {
        val scale = context.resources.displayMetrics.density;

        // convert from px to dp
        fun Int.pxToDp(): Int {
            return (this * scale + 0.5f).toInt()
        }

        mView = EditText(context)
        val p = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        p.setMargins(marginStart.pxToDp(), marginTop.pxToDp(), marginEnd.pxToDp(), marginBottom.pxToDp())
        mView!!.layoutParams = p

        mView!!.hint = view.prompt
    }

    override fun setValidation(validator: Validator) {
        this.validator = validator
    }

    override fun getValidation(): Validator {
        return validator?: throw NullPointerException("there is no validation")
    }

    override fun setInputDataChanged(checkValidation: (text: String) -> Boolean?) {
        RxTextView.textChanges(mView as TextView)
            .subscribe {
                val isValid = checkValidation(it.toString())
                if (isValid != null && !isValid) {
                    setError(validator!!.message)
                }
            }
    }

    override fun setError(message: String) {
        mView!!.error = message
    }

    override fun removeError() {
        mView!!.error = null;
    }

    override fun getView(): View {
        return mView!!
    }
}