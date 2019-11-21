package com.eb.event365kotlin.screens.home

import android.util.Log
import com.eb.event365kotlin.common.base.BaseViewModel
import com.eb.event365kotlin.common.schedulers.SchedulerProvider
import com.eb.event365kotlin.repository.HomeRepository
import com.google.gson.JsonElement



class HomeViewModel (schedulerProvider: SchedulerProvider,
                     private val repository: HomeRepository):BaseViewModel(schedulerProvider){

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

    private fun showResult(it: JsonElement?) {
        Log.i("yes",it.toString());
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("view model","cleared")
    }

}