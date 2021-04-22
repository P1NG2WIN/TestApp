package com.example.testapp.di

import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.module

object NavigationModule {

    fun navigationModule() = module {

        single { Cicerone.create() }

    }

}