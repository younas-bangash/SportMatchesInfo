package com.sport.matchesinfo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sport.matchesinfo.R
import com.sport.matchesinfo.viewmodels.MatchesListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MatchesListFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel = ViewModelProvider(this, viewModelFactory).get(MatchesListViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_list_fragment, container, false)
    }
}