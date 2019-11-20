package com.eb.event365kotlin.repository

import androidx.lifecycle.MutableLiveData
import com.eb.event365kotlin.common.ApiService
import com.eb.event365kotlin.common.extensions.APIResponse
import com.eb.event365kotlin.common.extensions.call
import com.google.gson.JsonElement
import io.reactivex.Observable
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(val apiService: ApiService){
    fun fetchProfile() = apiService.getProfile("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMwMiwiaWF0IjoxNTc0MjQ1NzAyfQ.PFvYfGNdqxBvYbEub0DVphJtaAMr9g25d2ZmGE6jXn4")
}