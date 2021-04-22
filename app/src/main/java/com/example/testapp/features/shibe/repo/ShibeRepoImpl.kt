package com.example.testapp.features.shibe.repo

import com.example.testapp.core.model.network.ResponseState
import com.example.testapp.features.shibe.repo.source.remote.RemoteShibeSource

class ShibeRepoImpl(
    private val remoteShibeSource: RemoteShibeSource
): ShibeRepo {

    override suspend fun loadShibeImages(): ResponseState<List<String>> {
        return remoteShibeSource.loadImages()
    }
}