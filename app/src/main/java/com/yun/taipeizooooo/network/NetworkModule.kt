package com.yun.taipeizooooo.network

import com.yun.taipeizooooo.repositories.DistrictRepository
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient() }
    single { ApiService(get()) }
}

val repositoriesModule = module {
    single { DistrictRepository(get()) }
}

val useCasesModule = module {}

val viewModelsModule = module {}