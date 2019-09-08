package com.baton.jsontoview.utils.command

import com.baton.jsontoview.data.entity.Validator
import java.util.regex.Pattern

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.Command
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class EditTextCommand(private var switchError: () -> Unit) : Command {

    override fun onInputEdit() {
    }

    override fun isValid(text: String, validation: Validator): Boolean {
        val p = Pattern.compile(validation.predicate.pattern)
        val m = p.matcher(text)

        if (!m.matches()) {
            switchError()
            return false
        }
        return true
    }
}