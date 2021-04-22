package com.example.testapp.core.model.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

interface RemoteCommonData {

    suspend fun <T> safeApiCall(
        call: suspend () -> Response<T>
    ): ResponseState<T> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response: Response<out T>? = call.invoke()

            response?.body()
                .takeIf { response?.isSuccessful == true && it != null }
                ?.let { ResponseState.Success(it) }
                ?: kotlin.run {
                    try {
                        val errorMessage = "unsuccessful api request; code: ${response?.code()}"

                        return@run ResponseState.Error(
                            ResponseException(errorMessage),
                            ErrorType.fromHttpCode(
                                response?.code(),
                                "unsuccessful api request"
                            )
                        )
                    } catch (e: Exception) {
                        ResponseState.Error(e, ErrorType.UnExceptedError)
                    }
                }

        } catch (e: Exception) {
            when (e) {
                is IOException ->
                    ResponseState.Error(e, ErrorType.NetworkError)

                is HttpException ->
                    ResponseState.Error(e, ErrorType.fromHttpCode(e.code()))

                else -> ResponseState.Error(e, ErrorType.UnExceptedError)
            }
        }
    }.also {
        if (it is ResponseState.Error)
            Timber.e("unsuccessful api request: $it")
    }

}