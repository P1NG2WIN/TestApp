package com.example.testapp.features.shibe.repo.source.remote

import com.example.testapp.core.model.network.ResponseState

interface RemoteShibeSource {

    suspend fun loadImages(): ResponseState<List<String>>

}