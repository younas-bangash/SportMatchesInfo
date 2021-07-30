package com.sport.matchesinfo.utils

import com.sport.matchesinfo.data.ApiError
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

/**
 * parses error response body
 */
object ErrorUtils {
    fun parseError(response: Response<*>, retrofit: Retrofit): ApiError? {
        val converter = retrofit.responseBodyConverter<ApiError>(ApiError::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            ApiError()
        }
    }
}