package com.example.testapp.di

import com.example.testapp.features.shibe.repo.ShibeRepo
import com.example.testapp.features.shibe.repo.ShibeRepoImpl
import com.example.testapp.features.shibe.repo.source.remote.RemoteShibeData
import com.example.testapp.features.shibe.repo.source.remote.RemoteShibeSource
import com.google.gson.GsonBuilder
import org.koin.dsl.module

object RepoModule {

    fun repoModule() = module {

        single<RemoteShibeSource> { RemoteShibeData(get()) }
        single<ShibeRepo> { ShibeRepoImpl(get()) }

        single {
            GsonBuilder()
                .setLenient()
                .create()
        }

    }

}