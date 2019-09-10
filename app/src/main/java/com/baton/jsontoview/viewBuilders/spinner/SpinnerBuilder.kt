package com.baton.jsontoview.viewBuilders.spinner

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.utils.pxToDp
import com.baton.jsontoview.viewBuilders.IViewBuilder
import com.baton.jsontoview.viewBuilders.factories.ViewPropertiesFactory

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class SpinnerBuilder(
    private var element: Element,
    private var context: Context,
    private var viewPropertiesFactory: ViewPropertiesFactory
) : IViewBuilder {

    private var mView: Spinner? = null

    private var viewProperty = viewPropertiesFactory.spinnerProperty

    private val marginTop = 4
    private val marginBottom = 4
    private val marginStart = 16
    private val marginEnd = 16


    override fun create(): View {

        val spinner = Spinner(context)

        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(marginStart.pxToDp(context), marginTop.pxToDp(context), marginEnd.pxToDp(context), marginBottom.pxToDp(context))
        spinner.layoutParams = layoutParams

        val personNames = element.view!!.widget.choices!!.map { it.title }
        val arrayAdapter = ArrayAdapter(context, R.layout.simple_spinner_item, personNames)
        spinner.adapter = arrayAdapter

        mView = spinner

        setOnInputDataChanged()

        return getView()
    }

    override fun setOnInputDataChanged() {
        mView!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text = element.view!!.widget.choices!![position].value
                viewProperty!!.field = element.name
                viewProperty!!.onInputEdit(text)
            }
        }
    }

    override fun getView(): View {
        return mView!!
    }
}