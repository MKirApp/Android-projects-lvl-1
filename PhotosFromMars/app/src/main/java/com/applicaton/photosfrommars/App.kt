package com.applicaton.photosfrommars

import android.app.Application
import com.applicaton.photosfrommars.di.AppComponent
import com.applicaton.photosfrommars.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}