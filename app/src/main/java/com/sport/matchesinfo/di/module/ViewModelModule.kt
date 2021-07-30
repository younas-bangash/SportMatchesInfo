package com.sport.matchesinfo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sport.matchesinfo.di.ViewModelFactory
import com.sport.matchesinfo.di.ViewModelKey
import com.sport.matchesinfo.viewmodels.MatchDetailsViewModel
import com.sport.matchesinfo.viewmodels.MatchesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module for the ViewModels that will be provided to the Fragments
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MatchesListViewModel::class)
    internal abstract fun bindMatchesListViewModel(viewModel: MatchesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MatchDetailsViewModel::class)
    internal abstract fun bindMatchDetailsViewModel(viewModel: MatchDetailsViewModel): ViewModel
}