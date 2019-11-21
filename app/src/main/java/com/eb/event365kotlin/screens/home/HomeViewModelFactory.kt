package com.eb.event365kotlin.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eb.event365kotlin.common.ApiService
import java.lang.IllegalArgumentException

class HomeViewModelFactory (val apiService: ApiService):ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown View Modal class")
    }
}