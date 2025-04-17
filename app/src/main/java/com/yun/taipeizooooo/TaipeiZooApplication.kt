 package com.yun.taipeizooooo

import android.app.Application
import com.yun.taipeizooooo.network.networkModule
import com.yun.taipeizooooo.network.repositoriesModule
import com.yun.taipeizooooo.network.useCasesModule
import com.yun.taipeizooooo.network.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaipeiZooApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@TaipeiZooApplication)
            modules(
                networkModule,
                repositoriesModule,
                useCasesModule,
                viewModelsModule
            )
        }
    }

}