package com.iml1s.epa.koin.module

import com.iml1s.epa.ShareViewModel
import com.iml1s.epa.main.datasource.EpaDatasource
import com.iml1s.epa.main.datasource.EpaDatasourceAPI
import com.iml1s.epa.main.repository.EpaRepository
import com.iml1s.epa.main.repository.EpaRepositoryImpl
import com.iml1s.epa.main.viewModel.EpaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EpaViewModel(get()) }
    viewModel { ShareViewModel() }
}
