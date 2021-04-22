package com.example.testapp.di

import com.example.testapp.features.generate.ui.GenerateCodeViewModel
import com.example.testapp.features.shibe.ui.ShibeViewModel
import com.example.testapp.features.start.ui.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelsModule {

    fun viewModelsModule() = module {

        viewModel { StartViewModel() }

        viewModel { ShibeViewModel(get()) }

        viewModel { GenerateCodeViewModel() }

    }

}