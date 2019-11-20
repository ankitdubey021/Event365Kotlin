package com.eb.event365kotlin.common.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel ()  : ViewModel(){

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()

    val loading: LiveData<Boolean> get() = _loading

    fun setLoad(state: Boolean) {
            _loading.value = state
    }

}