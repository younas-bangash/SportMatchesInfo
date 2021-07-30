package com.sport.matchesinfo.di.module

import com.sport.matchesinfo.view.MatchDetailsFragment
import com.sport.matchesinfo.view.MatchesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Include all the Fragments for which there can be Injection possible
 * let dagger know that a given Fragment is a potential client
 */

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMatchesListFragment(): MatchesListFragment

    @ContributesAndroidInjector
    abstract fun contributeMatchDetailsFragment(): MatchDetailsFragment
}