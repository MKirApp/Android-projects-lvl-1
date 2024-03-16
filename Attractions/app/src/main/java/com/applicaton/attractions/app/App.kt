package com.applicaton.attractions.app

import android.app.Application
import com.applicaton.attractions.di.AppComponent
import com.applicaton.attractions.di.AppModule
import com.applicaton.attractions.di.DaggerAppComponent

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(
                AppModule(context = this)
            ).build()

    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

}