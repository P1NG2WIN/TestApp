package com.example.testapp.core.model

sealed class Event<D, E> {

    companion object {
        fun <D, E> Event<D, E>.successData()
            = (this as? Success<D, E>)?.data
    }

    class Success<D, E>(
        val data: D? = null,
        val message: String? = null
    ): Event<D, E>()

    class Loading<D, E>: Event<D, E>()

    class Error<D, E>(
        val error: E? = null,
        val message: String? = null
    ): Event<D, E>()

}
