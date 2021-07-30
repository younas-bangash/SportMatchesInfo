package com.sport.matchesinfo.data.local

import com.sport.matchesinfo.data.ApiResult
import com.sport.matchesinfo.data.NetworkClient
import retrofit2.Response

/**
 * Mock Client for the API calls
 */
class MockNetworkClient : NetworkClient() {
    var mockResponse = String  // json file name to mock the response

    override suspend fun <T> getResponse(request: suspend () -> Response<T>): ApiResult<T> {
        return super.getResponse(request)
    }
}