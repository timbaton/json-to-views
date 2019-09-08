package com.baton.jsontoview.utils.viewBuilder

import android.content.Context
import android.view.View
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.data.entity.ViewType
import com.baton.jsontoview.utils.viewProperty.ViewPropertiesFactory
import javax.inject.Inject


/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.viewBuilder
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 **/

class ViewBuilderFactory @Inject constructor(
    private var context: Context,
    private var viewPropertiesFactory: ViewPropertiesFactory
) {

    private fun createMyEditText(element: Element): View {

        return EditTextBuilder(element, context, viewPropertiesFactory).create()
    }

    private fun createRadio(element: Element): View {
        return SpinnerBuilder(element, context, viewPropertiesFactory).create()
    }

    fun createView(element: Element): View? {
        if (element.view == null) return null

        return if (element.view.widget.type == ViewType.RADIO) {
            createRadio(element)
        } else {
            createMyEditText(element)
        }
    }

}