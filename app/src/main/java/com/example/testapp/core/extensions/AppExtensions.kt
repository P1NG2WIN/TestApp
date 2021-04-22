package com.example.testapp.core.extensions

import android.content.Context
import com.example.testapp.R
import com.example.testapp.core.model.network.ErrorType


fun ErrorType.defaultMessage(context: Context): String {
    return when (this) {
        is ErrorType.ClientError -> errorMessage ?: context.getString(R.string.error_type_client)
        is ErrorType.ServerError -> context.getString(R.string.error_type_server_error)
        ErrorType.UnAuthError -> context.getString(R.string.error_type_unauthenticated)
        ErrorType.NetworkError -> context.getString(R.string.error_type_network)
        ErrorType.UnExceptedError -> context.getString(R.string.error_type_unexpected)
    }
}