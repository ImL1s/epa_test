package com.iml1s.epa

import android.app.Application
import com.iml1s.epa.koin.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class EpaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }

    private fun initKoin() = startKoin {
        androidContext(this@EpaApplication)
        modules(networkModule, viewModelModule, repositoryModule, dataSourceModule, serviceModule)
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }
}