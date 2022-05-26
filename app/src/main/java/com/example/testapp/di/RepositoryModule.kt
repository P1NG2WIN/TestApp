package com.example.testapp.di

import com.example.testapp.features.shibe.repository.ShibeRepository
import com.example.testapp.features.shibe.repository.ShibeRepositoryImpl
import com.example.testapp.features.shibe.repository.source.remote.ShibeRemoteData
import com.example.testapp.features.shibe.repository.source.remote.ShibeRemoteSource
import com.google.gson.GsonBuilder
import org.koin.dsl.module

object RepositoryModule {

    fun repoModule() = module {

        single<ShibeRemoteSource> { ShibeRemoteData(get()) }
        single<ShibeRepository> { ShibeRepositoryImpl(get()) }

        single {
            GsonBuilder()
                .setLenient()
                .create()
        }

    }

}