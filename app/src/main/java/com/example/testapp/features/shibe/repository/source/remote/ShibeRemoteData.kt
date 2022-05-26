package com.example.testapp.features.shibe.repository.source.remote

import com.example.testapp.core.model.network.RemoteCommonData
import com.example.testapp.core.model.network.ResponseState
import com.example.testapp.features.shibe.model.remote.ShibeApi

class ShibeRemoteData(
    private val shibeApi: ShibeApi
): ShibeRemoteSource, RemoteCommonData {

    override suspend fun loadImages(): ResponseState<List<String>> {
        val response = safeApiCall { shibeApi.loadImages() }

        return response.mapResponse { this }
    }


}