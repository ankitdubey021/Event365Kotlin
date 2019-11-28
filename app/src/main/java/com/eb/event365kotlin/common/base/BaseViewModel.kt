package com.eb.event365kotlin.common.base


import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel : ViewModel(), CoroutineScope{


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob() + handler

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    private val _throwable : MutableLiveData<Throwable> = MutableLiveData()
    private val _authError : MutableLiveData<Boolean> = MutableLiveData()

    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _throwable
    val authError: LiveData<Boolean> get() = _authError

    fun changeState(load:Boolean=false,authError: Boolean?=false, error:Throwable?=null){
        _loading.postValue(load)
        _authError.postValue(authError)
        _throwable.postValue(error)
    }


    @CallSuper
    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("something went wrong",exception.message)
        if (exception is HttpException && exception.code()==401)
            changeState(authError = true)
        else
            changeState(error = exception)
    }

}