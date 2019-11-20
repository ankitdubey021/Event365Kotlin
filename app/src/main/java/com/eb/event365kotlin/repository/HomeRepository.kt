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

    fun fetchProfile() : MutableLiveData<APIResponse> {

        val mutableLiveData:  MutableLiveData<APIResponse> = MutableLiveData()

        val call = apiService.getProfile("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMwMiwiaWF0IjoxNTc0MjQ1NzAyfQ.PFvYfGNdqxBvYbEub0DVphJtaAMr9g25d2ZmGE6jXn4")

        val apiResponse = APIResponse()

        call.enqueue(object : Callback<JsonElement> {

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    apiResponse.isSuccess = true
                    try {
                        val OBJ = JSONObject(response.body()!!.toString())
                        apiResponse.message = OBJ.getString("message")
                        apiResponse.data = OBJ.getString("data")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    try {
                        apiResponse.isSuccess = false
                        apiResponse.code = response.code()
                        val OBJ = JSONObject(response.errorBody()!!.string())
                        apiResponse.message=OBJ.getString("message")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                mutableLiveData.postValue(apiResponse)
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

                apiResponse.isSuccess = false
                apiResponse.message = "Something went wrong!"

                mutableLiveData.postValue(apiResponse)
            }
        })
        return mutableLiveData;
    }
}