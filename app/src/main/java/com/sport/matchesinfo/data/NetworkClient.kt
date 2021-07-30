package com.sport.matchesinfo.data

import android.app.Application
import com.sport.matchesinfo.MainApplication
import com.sport.matchesinfo.api.Webservice
import com.sport.matchesinfo.utils.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * This class will be used to invoke the service call
 */
open class NetworkClient {

    @Inject
    lateinit var retrofit: Retrofit

    open suspend fun <T> getResponse(
        request: suspend () -> Response<T>
    ): ApiResult<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return ApiResult.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                ApiResult.error(
                    errorResponse?.status_message ?: "Unable to Fetch Internal Server Error",
                    errorResponse
                )
            }
        } catch (e: Throwable) {
            ApiResult.error("Internal Server Error", null)
        }
    }
}