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

class MatchesListViewModel @Inject constructor(private val repository: MatchesListRepository) :
    ViewModel() {
    val matchesListResponseMutableLiveData = MutableLiveData<ApiResult<ArrayList<MatchDetails>>>()

    internal fun fetchMovies() {
        viewModelScope.launch {
            repository.fetchMatchesList("bc1ce3b7-6ad2-4fef-af6c-08f8865b210e")
                .collect {
                    matchesListResponseMutableLiveData.value = it
                }
        }
    }
}