package com.sport.matchesinfo.api

import com.sport.matchesinfo.data.MatchDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for the API EndPoints
 */
interface Webservice {
    @GET("{token}")
    suspend fun getRequest(@Path("token") token: String): Response<ArrayList<MatchDetails>>
}