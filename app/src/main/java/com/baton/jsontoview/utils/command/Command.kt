package com.baton.jsontoview.utils.command

import com.baton.jsontoview.data.entity.Validator

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.Command
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
interface Command {

    fun onInputEdit()

    fun isValid(text: String, validation: Validator): Boolean
}