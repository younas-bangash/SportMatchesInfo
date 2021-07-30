package com.sport.matchesinfo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.sport.matchesinfo.adapters.MoviesListAdapter
import com.sport.matchesinfo.data.ApiResult
import com.sport.matchesinfo.databinding.MatchesListFragmentBinding
import com.sport.matchesinfo.viewmodels.MatchesListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Fragment to display the list of movies
 */
class MatchesListFragment : DaggerFragment() {
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: MoviesListAdapter
    val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(MatchesListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MatchesListFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        subscribeUi(binding)
        initUi(binding)
        return binding.root
    }

    private fun initUi(binding: MatchesListFragmentBinding) {
        swipeRefreshLayout = binding.swipeRefreshLayout;
        val layoutManager = LinearLayoutManager(context)
        binding.moviesList.layoutManager = layoutManager
        adapter = MoviesListAdapter(ArrayList())
        binding.moviesList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            viewModel.fetchMovies()
        }
    }

    private fun subscribeUi(binding: MatchesListFragmentBinding) {
        viewModel.matchesListResponseMutableLiveData.observe(viewLifecycleOwner, { result ->
            when (result.status) {
                ApiResult.Status.SUCCESS -> {
                    swipeRefreshLayout.isRefreshing = false
                    result.data?.let { list ->
                        adapter.updateData(list)
                    }
                    binding.loading.visibility = View.GONE
                }

                ApiResult.Status.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                    result.message?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                    binding.loading.visibility = View.GONE
                }

                ApiResult.Status.LOADING -> {
                    if (swipeRefreshLayout.isRefreshing) {
                        binding.loading.visibility = View.GONE
                    } else {
                        binding.loading.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}