package com.iml1s.epa.main.repository

import com.iml1s.epa.main.model.EpaResponse
import kotlinx.coroutines.flow.Flow

interface EpaRepository {

    fun getEpaData(): Flow<EpaResponse>
}