package com.eb.event365kotlin.common

import com.eb.event365kotlin.common.models.UserDao
import com.eb.event365kotlin.common.models.base.BaseDao
import com.google.gson.JsonElement
import io.reactivex.Observable

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("profileDetail")
    fun getProfile(@Header("Authorization") token: String): Observable<UserDao>

}