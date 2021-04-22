package com.example.testapp.features.generate.model.remote

import com.example.testapp.features.generate.model.remote.response.GenerateCodeResponse
import retrofit2.Response
import retrofit2.http.POST

interface GenerateCodeApi {

    @POST("/code")
    suspend fun generateCode(): Response<GenerateCodeResponse>

}