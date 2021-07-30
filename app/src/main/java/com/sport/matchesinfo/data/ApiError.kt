package com.sport.matchesinfo.data

/**
 * Data class for the Error Response
 */
data class ApiError(
    val status_code: Int = 0,
    val status_message: String? = null
)
