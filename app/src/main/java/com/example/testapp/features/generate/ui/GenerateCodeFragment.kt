package com.example.testapp.features.generate.ui

import androidx.fragment.app.Fragment
import com.example.testapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateCodeFragment : Fragment(R.layout.fragment_generate_code) {

    companion object {
        fun newInstance() = GenerateCodeFragment()
    }

    private val viewModel by viewModel<GenerateCodeViewModel>()

}