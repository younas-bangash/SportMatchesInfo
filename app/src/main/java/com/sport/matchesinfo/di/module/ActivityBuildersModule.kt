package com.sport.matchesinfo.di.module

import com.sport.matchesinfo.view.FragmentContainerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Include all the Activities for which there can be Injection possible
 * let dagger know that a given activity is a potential client
 */
@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])  //connecting  to the activity’s lifecycle
    abstract fun contributeMyActivity(): FragmentContainerActivity
}