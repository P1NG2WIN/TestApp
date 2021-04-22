package com.example.testapp.features.shibe.repo.source.remote

import com.example.testapp.core.model.network.RemoteCommonData
import com.example.testapp.core.model.network.ResponseState
import com.example.testapp.features.shibe.model.remote.ShibeApi

class RemoteShibeData(
    private val shibeApi: ShibeApi
): RemoteShibeSource, RemoteCommonData {

    override suspend fun loadImages(): ResponseState<List<String>> {
        val response = safeApiCall { shibeApi.loadImages() }

        return response.mapResponse { this }
    }


}