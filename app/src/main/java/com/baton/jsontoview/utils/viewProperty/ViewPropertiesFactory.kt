package com.baton.jsontoview.utils.viewProperty

import javax.inject.Inject

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils.ViewProperty
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */
class ViewPropertiesFactory @Inject constructor() {

    var spinnerCommand: SpinnerViewProperty? = null

    var editTextCommand: EditTextViewProperty? = null

}