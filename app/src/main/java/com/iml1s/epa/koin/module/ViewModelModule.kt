package com.iml1s.epa.koin.module

import com.iml1s.epa.ShareViewModel
import com.iml1s.epa.main.viewModel.EpaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EpaViewModel() }
    viewModel { ShareViewModel() }
}