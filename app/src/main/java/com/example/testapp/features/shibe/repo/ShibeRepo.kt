package com.example.testapp.features.shibe.repo

import com.example.testapp.core.model.network.ResponseState

interface ShibeRepo {

    suspend fun loadShibeImages(): ResponseState<List<String>>

}