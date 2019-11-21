package com.eb.event365kotlin.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eb.event365kotlin.common.ApiService
import com.eb.event365kotlin.common.base.BaseViewModel
import com.eb.event365kotlin.common.models.User
import com.eb.event365kotlin.common.models.UserDao
import com.eb.event365kotlin.common.utils.AUTH

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeViewModel(val apiService: ApiService) : BaseViewModel(){

    private val _userDao: MutableLiveData<User> = MutableLiveData()
    val userDao : LiveData<User> get() = _userDao

    init {
        Log.e("view model","started!")
    }

    fun loadPosts(){
        add {
            apiService.getProfile(AUTH)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { setLoad(true) }
                .doOnTerminate { setLoad(false) }
                .subscribe(
                    { showResult(it)},
                    { setError(it)}
                )
        }
    }

    private fun showResult(it: UserDao) {
        _userDao.postValue(it.data.get(0))
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("view model","cleared")
    }

}