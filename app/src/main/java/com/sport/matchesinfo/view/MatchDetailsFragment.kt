package com.sport.matchesinfo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sport.matchesinfo.databinding.MatchDetailsFragmentBinding
import com.sport.matchesinfo.viewmodels.MatchDetailsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Fragment to Display the Match Details
 */
class MatchDetailsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val matchDetailsFragmentArgs: MatchDetailsFragmentArgs by navArgs()
    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MatchDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MatchDetailsFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        viewModel.matchDetails = matchDetailsFragmentArgs.itemDetails
        binding.viewModel = viewModel
        return binding.root
    }
}