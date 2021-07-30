package com.sport.matchesinfo.view

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.*
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
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
        viewModel.fetchMovies(isNetworkAvailable())
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
            viewModel.fetchMovies(isNetworkAvailable())
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
                    binding.moviesList.visibility = View.VISIBLE
                }

                ApiResult.Status.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                    result.message?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                    binding.loading.visibility = View.GONE
                    binding.moviesList.visibility = View.VISIBLE
                }

                ApiResult.Status.LOADING -> {
                    binding.moviesList.visibility = View.GONE
                    if (swipeRefreshLayout.isRefreshing) {
                        binding.loading.visibility = View.GONE
                    } else {
                        binding.loading.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = requireContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }
}