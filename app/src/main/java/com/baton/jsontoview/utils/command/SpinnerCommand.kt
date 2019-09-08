package com.baton.jsontoview.utils.command

import com.baton.jsontoview.data.entity.Validator

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.Command
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class SpinnerCommand(private var action: () -> Unit) : Command {
    override fun onInputEdit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isValid(text: String, validation: Validator): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}