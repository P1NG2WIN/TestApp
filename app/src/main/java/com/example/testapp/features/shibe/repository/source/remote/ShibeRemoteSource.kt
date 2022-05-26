package com.example.testapp.features.shibe.repository.source.remote

import com.example.testapp.core.model.network.ResponseState

interface ShibeRemoteSource {

    suspend fun loadImages(): ResponseState<List<String>>

}