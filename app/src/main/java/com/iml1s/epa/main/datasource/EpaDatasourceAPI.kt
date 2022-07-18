package com.iml1s.epa.main.datasource

import com.iml1s.epa.main.model.EpaResponse
import com.iml1s.epa.main.service.EpaService
import kotlinx.coroutines.flow.Flow

class EpaDatasourceAPI(private val epaService: EpaService) : EpaDatasource {

    override fun getEpaData(): Flow<EpaResponse> {
        return epaService.getEpaData()
    }

}