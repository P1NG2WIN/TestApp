package com.example.testapp.features.shibe.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.core.base.BaseFragment
import com.example.testapp.core.extensions.defaultMessage
import com.example.testapp.core.model.Event
import com.example.testapp.databinding.FragmentShibeBinding
import com.example.testapp.features.shibe.ui.adapter.ShibeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShibeFragment : BaseFragment(R.layout.fragment_shibe) {

    companion object {
        fun newInstance() = ShibeFragment()
    }

    private val viewModel by viewModel<ShibeViewModel>()
    private val viewBinding by viewBinding<FragmentShibeBinding>()

    private val shibeAdapter by lazy { ShibeAdapter().createAdapter {  } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initVM()

    }

    override fun initView() {
        with(viewBinding) {
            root.setOnRefreshListener { viewModel.load() }

            rvFrMain.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = shibeAdapter
                setHasFixedSize(true)
            }
        }

    }

    override fun initVM() {
        viewModel.shibeLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Event.Success -> {
                    hideLoading()

                    shibeAdapter.items = it.data
                }

                is Event.Loading -> {
                    showLoading()
                }

                is Event.Error -> {
                    hideLoading()

                    Toast.makeText(
                        requireContext(),
                        it.error?.defaultMessage(requireContext()) ?: "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showLoading() {
        with(viewBinding) {
            root.isRefreshing = true
        }
    }
    private fun hideLoading() {
        with(viewBinding) {
            root.isRefreshing = false
        }
    }

}