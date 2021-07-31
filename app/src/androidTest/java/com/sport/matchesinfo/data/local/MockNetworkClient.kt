package com.sport.matchesinfo.data.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sport.matchesinfo.data.ApiResult
import com.sport.matchesinfo.data.NetworkClient
import retrofit2.Response

/**
 * Mock Client for the API calls
 */
class MockNetworkClient : NetworkClient() {
    lateinit var mockResponseFilePath: String  // json file name to mock the response

    override suspend fun <T> getResponse(request: suspend () -> Response<T>): ApiResult<T> {
        val fileContent = this::class.java.classLoader.getResource(mockResponseFilePath).readText()
        val response : T =
            Gson().fromJson(fileContent, object : TypeToken<T>() {}.type)
        return ApiResult.success(response)
    }
}