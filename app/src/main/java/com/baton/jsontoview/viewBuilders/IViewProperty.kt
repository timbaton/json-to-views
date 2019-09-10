package com.baton.jsontoview.viewBuilders

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.IViewProperty
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
interface IViewProperty {

    fun onInputEdit(state: String)

    fun isValid(text: String, pattern: String): Boolean
}