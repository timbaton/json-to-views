package com.baton.jsontoview.viewBuilders.factories

import com.baton.jsontoview.viewBuilders.spinner.SpinnerViewProperty
import com.baton.jsontoview.viewBuilders.editText.EditTextViewProperty
import javax.inject.Inject

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.IViewProperty
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class ViewPropertiesFactory @Inject constructor() {

    var spinnerProperty: SpinnerViewProperty? = null

    var editTextProperty: EditTextViewProperty? = null

}