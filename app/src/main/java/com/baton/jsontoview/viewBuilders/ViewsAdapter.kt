package com.baton.jsontoview.viewBuilders

import android.view.View
import com.baton.jsontoview.data.entity.Content
import com.baton.jsontoview.data.entity.Element
import com.baton.jsontoview.data.entity.ElementType
import com.baton.jsontoview.utils.enums.Tags
import com.baton.jsontoview.viewBuilders.factories.ViewBuilderFactory
import com.baton.jsontoview.viewBuilders.factories.ViewPropertiesFactory
import javax.inject.Inject


/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */

class ViewsAdapter @Inject constructor(
    private var viewBuilderFactory: ViewBuilderFactory,
    private var viewPropertiesFactory: ViewPropertiesFactory
) {

    private val views = mutableListOf<View>()
    private val dependentViews = mutableListOf<View>()

    fun getParsedViews(content: Content): List<View> {
        content.elements.forEach { element ->
            if (element.type == ElementType.FIELD) {
                createView(element)
            } else if (element.type == ElementType.DEPENDENCY) {
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

                //set tag for distinguishing kind of dependence to hide / show the view
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

            //if the current state of the dependency type is correct - show the view
            val state = viewPropertiesFactory.spinnerProperty!!.states[dependentField]
            if (viewPropertiesFactory.spinnerProperty!!.isValid(state.toString(), dependentPattern)) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }

        return views
    }
}

