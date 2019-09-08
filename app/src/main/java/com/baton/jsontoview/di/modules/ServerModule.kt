package com.baton.jsontoview.di.modules

import com.baton.jsontoview.data.ViewsRepository
import com.baton.jsontoview.data.ViewApi
import com.example.kyrs.di.ServerPath
import com.baton.jsontoview.di.providers.ApiProvider
import com.baton.jsontoview.di.providers.OkHttpClientProvider
import com.baton.jsontoview.utils.viewProperty.ViewPropertiesFactory
import com.baton.jsontoview.utils.viewProperty.ViewProperty
import okhttp3.OkHttpClient
import toothpick.config.Module

/**
 * Project jsontoview
 * Package com.baton.jsontoview.di
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */
class ServerModule(baseUrl: String) : Module() {

    init {

        bind(String::class.java).withName(ServerPath::class.java).toInstance(baseUrl)

        // Retrofit
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingleton()
        bind(ViewApi::class.java).toProvider(ApiProvider::class.java).providesSingleton()

        // Repositories
        bind(ViewsRepository::class.java).singleton()

        bind(ViewPropertiesFactory::class.java).singleton()
    }
}