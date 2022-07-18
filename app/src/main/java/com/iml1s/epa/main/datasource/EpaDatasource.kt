package com.iml1s.epa.main.datasource

import com.iml1s.epa.main.model.EpaResponse
import kotlinx.coroutines.flow.Flow

interface EpaDatasource {

    fun getEpaData(): Flow<EpaResponse>
}