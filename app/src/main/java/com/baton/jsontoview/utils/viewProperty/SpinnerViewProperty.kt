package com.baton.jsontoview.utils.viewProperty

import java.util.regex.Pattern

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.ViewProperty
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class SpinnerViewProperty(private var action: () -> Unit) : ViewProperty {

    var states = mutableMapOf<String, Int>()
    var field: String = ""

//    val state = MutableLiveData<Int>()


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