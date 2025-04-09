package com.yun.taipeizooooo.network

import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient() }
    single { ApiService(get()) }
}
val repositoriesModule = module {}
val useCasesModule = module {}
val viewModelsModule = module {}