package com.baton.jsontoview.utils.viewProperty

import java.util.regex.Pattern

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.ViewProperty
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class EditTextViewProperty(private var switchError: () -> Unit) : ViewProperty {

    override fun onInputEdit(state: String) {
    }

    override fun isValid(text: String, pattern: String): Boolean {
        val p = Pattern.compile(pattern)
        val m = p.matcher(text)

        if (!m.matches()) {
            switchError()
            return false
        }
        return true
    }
}