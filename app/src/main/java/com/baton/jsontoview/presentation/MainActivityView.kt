package com.baton.jsontoview.presentation

import android.view.View
import com.arellomobile.mvp.MvpView

/**
 * Project jsontoview
 * Package com.baton.jsontoview.ui
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */
interface MainActivityView : MvpView {

    fun showError()
    fun showViews(views: List<View>)
}