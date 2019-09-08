package com.baton.jsontoview.di.providers

import com.baton.jsontoview.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

/**
 * Project Kyrs
 * Package com.example.kyrs.di.providers
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-02
 */
class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

    private val connectionTimeout = 30L
    private val readTimeout = 30L

    override fun get(): OkHttpClient = with(OkHttpClient.Builder()) {
        connectTimeout(connectionTimeout, TimeUnit.SECONDS)
        readTimeout(readTimeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        addInterceptor {
            val request = it.request().newBuilder().addHeader("Content-Type", "application/json").build()
            it.proceed(request)
        }
        build()
    }
}