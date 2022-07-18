package com.iml1s.epa.koin.module

import com.iml1s.epa.main.repository.EpaRepository
import com.iml1s.epa.main.repository.EpaRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<EpaRepository> { EpaRepositoryImpl(get()) }
}