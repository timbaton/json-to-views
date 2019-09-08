package com.baton.jsontoview.utils.viewBuilder

import android.content.Context
import android.view.View
import com.baton.jsontoview.data.entity.MyView
import com.baton.jsontoview.data.entity.Validator

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
interface IMyView {

    fun create(view: MyView, context: Context)

    fun setInputDataChanged(checkValidation: (text: String) -> Boolean?)

    fun setValidation(validator: Validator)

    fun setError(message: String)

    fun removeError()

    fun getValidation(): Validator

    fun getView(): View
}