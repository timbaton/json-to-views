package com.baton.jsontoview.viewBuilders.spinner

import com.baton.jsontoview.viewBuilders.IViewProperty
import java.util.regex.Pattern

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.IViewProperty
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class SpinnerViewProperty(private var action: () -> Unit) : IViewProperty {

    var states = mutableMapOf<String, Int>()
    var field: String = ""

    override fun onInputEdit(state: String) {

        states[field] = state.toInt()
        action()
    }

    override fun isValid(text: String, pattern: String): Boolean {
        val p = Pattern.compile(pattern)
        val m = p.matcher(text)

        if (!m.matches()) {
            return false
        }
        return true
    }
}