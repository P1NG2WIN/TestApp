package com.example.testapp.features.start.ui

import android.os.Bundle
import android.view.View
import com.example.testapp.R
import com.example.testapp.core.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : BaseFragment(R.layout.fragment_start) {

    companion object {
        fun newInstance() = StartFragment()
    }

    private val viewModel by viewModel<StartViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initVM()

    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initVM() {
        TODO("Not yet implemented")
    }

}