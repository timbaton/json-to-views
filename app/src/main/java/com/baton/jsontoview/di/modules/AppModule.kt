package com.baton.jsontoview.di.modules

import android.content.Context
import com.baton.jsontoview.data.ViewsRepository
import com.baton.jsontoview.data.ViewApi
import com.example.kyrs.di.ServerPath
import com.baton.jsontoview.di.providers.ApiProvider
import com.baton.jsontoview.di.providers.OkHttpClientProvider
import okhttp3.OkHttpClient
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