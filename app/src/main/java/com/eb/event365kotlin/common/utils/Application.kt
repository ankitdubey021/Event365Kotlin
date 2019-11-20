package com.eb.event365kotlin.common.utils

import androidx.multidex.MultiDexApplication
import com.eb.event365kotlin.common.applicationModules

import org.koin.android.ext.android.startKoin

class Application : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(applicationModules),
            loadPropertiesFromFile = true
        )
    }
}
