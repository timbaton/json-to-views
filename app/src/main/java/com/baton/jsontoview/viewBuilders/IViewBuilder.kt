package com.baton.jsontoview.viewBuilders

import android.view.View

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
interface IViewBuilder {

    fun create(): View

    fun setOnInputDataChanged()

    fun getView(): View
}