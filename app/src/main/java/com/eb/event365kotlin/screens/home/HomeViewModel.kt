package com.eb.event365kotlin.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eb.event365kotlin.common.base.BaseViewModel
import com.eb.event365kotlin.common.models.User
import com.eb.event365kotlin.common.models.UserDao
import com.eb.event365kotlin.common.schedulers.SchedulerProvider
import com.eb.event365kotlin.repository.HomeRepository



class HomeViewModel (schedulerProvider: SchedulerProvider,
                     private val repository: HomeRepository):BaseViewModel(schedulerProvider){

    private val _userDao: MutableLiveData<User> = MutableLiveData()
    val userDao : LiveData<User> get() = _userDao

    init {
        Log.e("view model","started!")
    }

    fun loadPosts(){
        add {
            repository.fetchProfile()
                .compose(applySchedulers())
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