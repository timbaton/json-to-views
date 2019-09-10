package com.baton.jsontoview.di.modules

import android.content.Context
import toothpick.config.Module

/**
 * Project jsontoview
 * Package com.baton.jsontoview.di
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */
class AppModule(context: Context) : Module() {

    init {
        bind(Context::class.java).toInstance(context)
    }
}