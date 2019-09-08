package com.baton.jsontoview.utils.viewBuilder

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.data.entity.MyView
import com.baton.jsontoview.utils.command.CommandFactory


/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 **/

class ViewBuilderFactory {

    private fun createMyEditText(element: Element, context: Context, commandFactory: CommandFactory): View {

        return with(MyEditText()) {
            create(element.view!!, context)
            setValidation(element.validator!!)
            setInputDataChanged {text ->
               commandFactory.editTextCommand?.isValid(text, getValidation())
            }

            getView()
        }
    }

    private fun createRadio(myView: MyView, context: Context, commandFactory: CommandFactory): View {
        val spinner = Spinner(context)
        spinner.layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val personNames = myView.widget.choices!!.map { it.title }
        val arrayAdapter = ArrayAdapter(context, R.layout.simple_spinner_item, personNames)
        spinner.adapter = arrayAdapter

//        spinner.setOn
        return spinner
    }

    fun createView(element: Element, context: Context, commandFactory: CommandFactory): View? {
        if (element.view == null) return null

        return if (element.view.widget.type == "radio") {
            createRadio(element.view, context, commandFactory)
        } else {
            createMyEditText(element, context, commandFactory)
        }
    }

}