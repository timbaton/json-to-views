package com.baton.jsontoview

import android.app.Application
import com.baton.jsontoview.di.Scopes
import com.baton.jsontoview.di.modules.AppModule
import com.baton.jsontoview.di.modules.ServerModule
import toothpick.Scope
import toothpick.Toothpick

/**
 * Project jsontoview
 * Package com.baton.jsontoview
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initToothpick()
    }

    private fun initToothpick() {
        val appScope = Toothpick.openScope(Scopes.App)
        appScope.installModules(AppModule(this))

        val serverScope = Toothpick.openScopes(Scopes.App, Scopes.Server)
        serverScope.installModules(ServerModule(BuildConfig.API_URL))
    }
}
