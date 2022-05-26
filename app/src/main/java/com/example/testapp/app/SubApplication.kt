package com.example.testapp.app

import android.app.Application
import com.example.testapp.di.NavigationModule
import com.example.testapp.di.NetworkModule
import com.example.testapp.di.RepositoryModule
import com.example.testapp.di.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SubApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SubApplication)
            modules(
                NavigationModule.navigationModule(),
                NetworkModule.okHttpModule(),
                NetworkModule.retrofitModule(),
                RepositoryModule.repoModule(),
                ViewModelsModule.viewModelsModule()
            )
        }
    }

}