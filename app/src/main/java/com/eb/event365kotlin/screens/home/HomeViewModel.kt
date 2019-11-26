package com.eb.event365kotlin.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eb.event365kotlin.common.ApiService
import com.eb.event365kotlin.common.base.BaseViewModel
import com.eb.event365kotlin.common.models.User
import com.eb.event365kotlin.common.models.UserDao
import com.eb.event365kotlin.common.utils.AUTH

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class HomeViewModel(val apiService: ApiService) : BaseViewModel(){

    private val _userDao: MutableLiveData<User> = MutableLiveData()
    val userDao : LiveData<User> get() = _userDao

    init {
        Log.e("view model","started!")
    }

    fun loadPosts(){
        GlobalScope.launch (Dispatchers.Main){
            val list=async (Dispatchers.IO) { apiService.getProfile(AUTH)}
            showResult(list.await())
        }
    }

    private fun showResult(it: UserDao) {
        _userDao.postValue(it.data.get(0))
    }

}