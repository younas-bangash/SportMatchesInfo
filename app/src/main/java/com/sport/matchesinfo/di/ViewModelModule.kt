package com.sport.matchesinfo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sport.matchesinfo.viewmodels.FragmentContainerViewModel
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
}