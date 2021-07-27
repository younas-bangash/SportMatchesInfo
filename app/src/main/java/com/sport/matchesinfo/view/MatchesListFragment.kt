package com.sport.matchesinfo.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sport.matchesinfo.R
import com.sport.matchesinfo.viewmodels.MatchesListViewModel

class MatchesListFragment : Fragment() {

    companion object {
        fun newInstance() = MatchesListFragment()
    }

    private lateinit var viewModel: MatchesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MatchesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}