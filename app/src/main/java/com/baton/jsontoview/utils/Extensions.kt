package com.baton.jsontoview.utils

import android.content.Context

/**
 * Project jsontoview
 * Package com.baton.jsontoview.utils
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-08
 *
 */

// convert from px to dp
fun Int.pxToDp(context: Context): Int {
    val scale = context.resources.displayMetrics.density;

    return (this * scale + 0.5f).toInt()
}
