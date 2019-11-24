package com.shrikantb.statemachine

import android.app.Application
import com.shrikantb.statemachine.data.di.networkModule
import com.shrikantb.statemachine.data.di.repositoryModule
import com.shrikantb.statemachine.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StateMachineApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StateMachineApplication)
            modules(networkModule)
            modules(viewModelModule)
            modules(repositoryModule)
        }
    }
}