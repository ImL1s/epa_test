package com.iml1s.epa.koin.module

import com.iml1s.epa.main.datasource.EpaDatasource
import com.iml1s.epa.main.datasource.EpaDatasourceAPI
import org.koin.dsl.module

val dataSourceModule = module {
    single<EpaDatasource> { EpaDatasourceAPI() }
}
