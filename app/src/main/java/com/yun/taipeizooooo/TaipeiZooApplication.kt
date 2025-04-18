 package com.yun.taipeizooooo

import android.app.Application
import com.yun.taipeizooooo.network.networkModule
import com.yun.taipeizooooo.network.repositoriesModule
import com.yun.taipeizooooo.network.useCasesModule
import com.yun.taipeizooooo.network.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaipeiZooApplication : Application() {

    companion object {
        private var instance: TaipeiZooApplication? = null
        fun getInstance(): TaipeiZooApplication {
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        instance = this
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