package com.sport.matchesinfo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.matchesinfo.data.ApiResult
import com.sport.matchesinfo.data.MatchDetails
import com.sport.matchesinfo.data.MatchesListRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * ViewModel for the [MatchesListFragment]
 */
class MatchesListViewModel @Inject constructor(private val repository: MatchesListRepository) :
    ViewModel() {
    val matchesListResponseMutableLiveData = MutableLiveData<ApiResult<List<MatchDetails>>>()

    internal fun fetchMovies(isNetworkAvailable: Boolean) {
        viewModelScope.launch {
            coroutineScope {
                val firstApiDeferredRes = async {
                    // First API Call
                    repository.fetchMatchesList(
                        isNetworkAvailable,
                        "23745f3a-5eaa-43cf-ab46-737eb273596b"
                    )
                }

                val secondApiDeferredRes = async {
                    // Second API Call
                    repository.fetchMatchesList(
                        isNetworkAvailable,
                        "bc1ce3b7-6ad2-4fef-af6c-08f8865b210e"
                    )
                }

                val firstApiResult = firstApiDeferredRes.await()
                val secondApiResult = secondApiDeferredRes.await()

                firstApiResult.collect {
                    matchesListResponseMutableLiveData.value = it
                }

                secondApiResult.collect {
                    matchesListResponseMutableLiveData.value = it
                }
            }
        }
    }
}