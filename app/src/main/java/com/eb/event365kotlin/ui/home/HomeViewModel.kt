package com.eb.event365kotlin.ui.home

import androidx.lifecycle.LiveData
import com.eb.event365kotlin.common.base.BaseViewModel
import com.eb.event365kotlin.common.extensions.APIResponse
import com.eb.event365kotlin.repository.HomeRepository

class HomeViewModel (private val repository: HomeRepository):BaseViewModel(){

    fun loadPosts():LiveData<APIResponse>{
            setLoad(true)
            val mutableLiveData = repository.fetchProfile()
            setLoad(false)
        return mutableLiveData
    }
}