package com.sport.matchesinfo.di

import android.content.Context
import com.sport.matchesinfo.MainApplication
import com.sport.matchesinfo.api.Webservice
import com.sport.matchesinfo.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Definition of the Application graph
 * @Component makes Dagger create a graph of dependencies
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        ViewModelModule::class,
        AppModule::class,
        DatabaseModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}