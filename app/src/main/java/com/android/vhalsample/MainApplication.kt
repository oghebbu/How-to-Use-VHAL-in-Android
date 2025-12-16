package com.android.vhalsample

import android.app.Application
import com.android.vhalsample.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val TAG = "VHALSampleApp"
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(applicationModule))
        }
    }
}