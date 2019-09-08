package com.baton.jsontoview.utils.viewBuilder

import android.view.View

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
interface IMyView {

    fun create(): View

    fun setInputDataChanged()

    fun getView(): View
}