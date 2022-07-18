package com.iml1s.epa.main.service

import com.iml1s.epa.BuildConfig
import com.iml1s.epa.main.model.EpaResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface EpaService {

    @GET("api/v1/aqx_p_432")
    fun getEpaData(
        @Query("limit") limit: Number = 1000,
        @Query("offset") offset: Number = 0,
        @Query("api_key") apiKey: String = BuildConfig.EPA_API_KEY,
        @Query("sort") sort: String = "ImportDate desc",
        @Query("format") format: String = "json"
    ): Flow<EpaResponse>

}