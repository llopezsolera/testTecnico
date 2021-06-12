package com.example.apppruebaindra.application

import android.app.Application
import com.example.apppruebaindra.di.viewModulesModule
import com.example.repository.BuildConfig
import com.example.repository.network.di.networkModule
import com.example.repository.network.di.retrofitModule
import com.example.repository.network.utils.BASE_URL
import com.example.usecase.di.usecasesModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppApplication)
            modules(
                    arrayListOf(
                            viewModulesModule,
                            networkModule,
                            usecasesModule,
                            retrofitModule
                    )
            )
        }
        getKoin().setProperty(BASE_URL, BuildConfig.BASE_URL)
    }

}