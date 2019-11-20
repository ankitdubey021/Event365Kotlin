package com.eb.event365kotlin.common
import com.google.gson.JsonElement

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("profileDetail")
    fun getProfile(@Header("Authorization") token: String): Call<JsonElement>

}