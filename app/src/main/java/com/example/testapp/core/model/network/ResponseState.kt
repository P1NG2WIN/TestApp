package com.example.testapp.core.model.network

import kotlin.Exception

sealed class ResponseState<out T> {
    fun successResult(): T? {
        return (this as? Success<T>)?.result
    }

    fun <N> mapResponse(mapper: T.() -> N): ResponseState<N> {
        return when (this) {
            is Success -> Success(mapper.invoke(result))
            is Error -> Error(throwable, errorType)
        }
    }


    data class Success<T>(val result: T) : ResponseState<T>()

    data class Error<T>(
        val throwable: Throwable?,
        val errorType: ErrorType
    ) : ResponseState<T>()
//    data class Error<T>(val exception: Exception? = null) : ResponseState<T>()

}

class ResponseException: Exception {

    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)

}

sealed class ErrorType {

    companion object {
        fun fromHttpCode(errorCode: Int?, message: String? = null): ErrorType {
            return when (errorCode) {
                401 ->
                    UnAuthError
                in 400..499 ->
                    ClientError(message, errorCode)
                in 500..599 ->
                    ServerError(errorCode)
                else ->
                    UnExceptedError
            }
        }
    }

    class ClientError(
        val errorMessage: String? = null,
        val errorCode: Int? = null
    ): ErrorType() /*400..499*/ {
        override fun toString(): String {
            return "ClientError(errorMessage=$errorMessage, errorCode=$errorCode)"
        }
    }

    class ServerError(
        val errorCode: Int? = null
    ): ErrorType() /*500..599*/ {
        override fun toString(): String {
            return "ServerError(errorCode=$errorCode)"
        }
    }

    object UnAuthError: ErrorType() /*401*/

    object NetworkError: ErrorType()

    object UnExceptedError: ErrorType()


}