package com.example.testapp.features.shibe.repository

import com.example.testapp.core.model.network.ResponseState

interface ShibeRepository {

    suspend fun loadShibeImages(): ResponseState<List<String>>

}