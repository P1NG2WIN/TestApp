package com.example.testapp.features.shibe.repository

import com.example.testapp.core.model.network.ResponseState
import com.example.testapp.features.shibe.repository.source.remote.ShibeRemoteSource

class ShibeRepositoryImpl(
    private val shibeRemoteSource: ShibeRemoteSource
): ShibeRepository {

    override suspend fun loadShibeImages(): ResponseState<List<String>> {
        return shibeRemoteSource.loadImages()
    }
}