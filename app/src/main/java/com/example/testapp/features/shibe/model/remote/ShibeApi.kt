package com.example.testapp.features.shibe.model.remote

import retrofit2.Response
import retrofit2.http.GET

interface ShibeApi {

    @GET("/api/shibes?count=20&httpsUrls=true")
    suspend fun loadImages(): Response<List<String>>

}