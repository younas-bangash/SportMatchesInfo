package com.sport.matchesinfo

import com.sport.matchesinfo.di.ApplicationComponent
import com.sport.matchesinfo.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * App Application Class
 */
open class MainApplication : DaggerApplication() {
    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent.factory().create(this)
        return appComponent
    }
}