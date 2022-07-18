package com.iml1s.epa.main.repository

import com.iml1s.epa.main.datasource.EpaDatasource
import com.iml1s.epa.main.model.EpaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class EpaRepositoryImpl(private val epaDatasource: EpaDatasource) : EpaRepository {

    override fun getEpaData(): Flow<EpaResponse> {
        return epaDatasource.getEpaData().flowOn(Dispatchers.IO)
    }

}