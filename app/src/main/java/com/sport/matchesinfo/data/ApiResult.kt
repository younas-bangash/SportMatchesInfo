package com.sport.matchesinfo.data

/**
 * Generic class for holding success response, error response and loading status
 */
data class ApiResult<out T>(
    val status: Status,
    val data: T?,
    val apiError: ApiError?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, apiError: ApiError?): ApiResult<T> {
            return ApiResult(Status.ERROR, null, apiError, message)
        }

        fun <T> loading(data: T? = null): ApiResult<T> {
            return ApiResult(Status.LOADING, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$apiError, message=$message)"
    }
}
