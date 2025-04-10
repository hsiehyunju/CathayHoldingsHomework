package com.yun.taipeizooooo.network

 import com.yun.taipeizooooo.domain.DistrictUseCase
import com.yun.taipeizooooo.repositories.DistrictRepository
import com.yun.taipeizooooo.viewModels.DistrictViewModel
import com.yun.taipeizooooo.viewModels.TaipeiZooActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient() }
    single { ApiService(get()) }
}

val repositoriesModule = module {
    single { DistrictRepository(get()) }
}

val useCasesModule = module {
    factory { DistrictUseCase(get()) }
}

val viewModelsModule = module {
    viewModel { TaipeiZooActivityViewModel() }
    viewModel { DistrictViewModel(get()) }
}