package com.example.testapp.features.generate.model.remote.response

import com.google.gson.annotations.SerializedName

class GenerateCodeResponse(
    @SerializedName("code") val code: String
)