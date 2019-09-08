package com.baton.jsontoview.utils.viewBuilder

import android.content.Context
import android.view.View
import com.baton.jsontoview.data.entity.Content
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.utils.command.CommandFactory
import javax.inject.Inject


/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */

class ViewCreator @Inject constructor(
    private var context: Context
) {

    private val views = mutableListOf<View>()
    private val viewsBuilder = ViewBuilderFactory()

    fun getParsedViews(content: Content, commandFactory: CommandFactory): List<View> {
        content.elements.forEach { element ->
            if (element.type == "field") {
                createView(element, commandFactory)?.let { createdView ->
                    views.add(createdView)
                }
            } else {
                getParsedViews(element.content!!, commandFactory)
            }
        }

        return views
    }

    private fun createView(element: Element, commandFactory: CommandFactory): View? {
        return viewsBuilder.createView(element, context, commandFactory)
    }
}

