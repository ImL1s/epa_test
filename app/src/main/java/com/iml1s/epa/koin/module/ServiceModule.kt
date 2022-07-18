package com.iml1s.epa.koin.module

import com.iml1s.epa.main.service.EpaService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    factory { provideEpaService(get()) }
}

fun provideEpaService(retrofit: Retrofit): EpaService =
    retrofit.create(EpaService::class.java)