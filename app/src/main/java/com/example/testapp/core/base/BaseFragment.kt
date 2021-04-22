package com.example.testapp.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import timber.log.Timber

abstract class BaseFragment(
    @LayoutRes val contentLayoutId: Int
): Fragment(contentLayoutId) {

    open val hasOptionMenu: Boolean = false

    abstract fun initView()
    abstract fun initVM()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Lifecycle - onCreate %s", this.javaClass.simpleName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("Lifecycle - onCreateView %s, Activity: %s", this.javaClass.simpleName, this.activity.toString())
        setHasOptionsMenu(hasOptionMenu)

        if (contentLayoutId == 0)
            return null

        val view = inflater.inflate(contentLayoutId, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("Lifecycle - onViewCreated %s", this.javaClass.simpleName)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("Lifecycle - onStart() %s", this.javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()
        Timber.i("Lifecycle - onStop() %s", this.javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Timber.i("Lifecycle - onResume() %s", this.javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Timber.i("Lifecycle - onPause() %s", this.javaClass.simpleName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("Lifecycle - onDestroyView() %s", this.javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("Lifecycle - onDestroy() %s", this.javaClass.simpleName)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("Lifecycle - onSaveInstanceState() %s", this.javaClass.simpleName)
    }
}