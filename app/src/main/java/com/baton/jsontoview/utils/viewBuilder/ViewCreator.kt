package com.baton.jsontoview.utils.viewBuilder

import android.view.View
import com.baton.jsontoview.data.entity.Content
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.utils.enums.ElementTypeEnum
import com.baton.jsontoview.utils.enums.Tags
import com.baton.jsontoview.utils.viewProperty.ViewPropertiesFactory
import javax.inject.Inject


/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */

class ViewCreator @Inject constructor(
    private var viewBuilderFactory: ViewBuilderFactory,
    private var viewPropertiesFactory: ViewPropertiesFactory
) {

    private val views = mutableListOf<View>()
    private val dependentViews = mutableListOf<View>()

    fun getParsedViews(content: Content): List<View> {
        content.elements.forEach { element ->
            if (element.type == ElementTypeEnum.FIELD.type) {
                createView(element)
            } else if (element.type == ElementTypeEnum.DEPENDENCY.type) {
                createDependentViews(element)
            }
        }

        return views
    }

    private fun createView(element: Element) {
        viewBuilderFactory.createView(element)?.let {
            views.add(it)
        }
    }

    private fun createDependentViews(element: Element) {
        element.content!!.elements.forEach {
            viewBuilderFactory.createView(it)?.let { createdView ->
                createdView.setTag(Tags.FIELD.tag, element.condition!!.field)
                createdView.setTag(Tags.PATTERN.tag, element.condition.predicate.pattern)

                views.add(createdView)
                dependentViews.add(createdView)
            }
        }
    }

    fun onSpinnerChanged(): List<View> {
        dependentViews.forEach { view ->
            val dependentField = view.getTag(Tags.FIELD.tag)
            val dependentPattern: String = view.getTag(Tags.PATTERN.tag) as String

            val state = viewPropertiesFactory.spinnerCommand!!.states[dependentField]
            if (viewPropertiesFactory.spinnerCommand!!.isValid(state.toString(), dependentPattern)) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }

        return views
    }
}

