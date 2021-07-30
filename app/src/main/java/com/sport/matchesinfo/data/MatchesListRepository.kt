package com.sport.matchesinfo.data

import com.sport.matchesinfo.api.Webservice
import com.sport.matchesinfo.utils.ErrorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class MatchesListRepository @Inject constructor(
    private val webserviceInterface: Webservice,
    private val networkClient: NetworkClient
) {
    suspend fun fetchMatchesList(token: String): Flow<ApiResult<ArrayList<MatchDetails>>?> {
        return flow {
            emit(ApiResult.loading())
            val result = networkClient.getResponse(
                request = { webserviceInterface.fetchMatchesList(token) }
            )
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}