package com.baton.jsontoview.utils.viewProperty

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.ViewProperty
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
interface ViewProperty {

    fun onInputEdit(state: String)

    fun isValid(text: String, pattern: String): Boolean
}