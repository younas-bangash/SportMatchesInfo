package com.sport.matchesinfo.data

import com.sport.matchesinfo.api.Webservice
import com.sport.matchesinfo.data.local.MatchDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class MatchesListRepository @Inject constructor(
    private val webserviceInterface: Webservice,
    private val networkClient: NetworkClient,
    private val matchDetailsDao: MatchDetailsDao
) {
    suspend fun fetchMatchesList(
        fetchFromServer: Boolean,
        token: String
    ): Flow<ApiResult<List<MatchDetails>>?> {
        return flow {
            emit(ApiResult.loading())
            if (!fetchFromServer) {
                // If network is not available fetch the record from DB
                emit(ApiResult.success(matchDetailsDao.getAll()))
            } else {
                val result = networkClient.getResponse(request = {
                    webserviceInterface.fetchMatchesList(token)
                })

                // Cache to database if response is successful
                if (result.status == ApiResult.Status.SUCCESS) {
                    result.data?.let { list ->
                        deleteAll(result.data)
                        matchDetailsDao.insertAll(list)
                        emit(ApiResult.success(matchDetailsDao.getAll()))
                    }
                } else {
                    emit(result)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun deleteAll(data: ArrayList<MatchDetails>) {
        // Clear the Previous Cache data
        for (item in data) {
            matchDetailsDao.deleteRow(
                item.firstTeamName,
                item.secondTeamName
            )
        }
    }
}