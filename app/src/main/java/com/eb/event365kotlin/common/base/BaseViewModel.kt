package com.eb.event365kotlin.common.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eb.event365kotlin.common.schedulers.SchedulerProvider


import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel (val schedulerProvider: SchedulerProvider)  : ViewModel(){

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    private val _throwable : MutableLiveData<Throwable> = MutableLiveData()

    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _throwable

    fun setLoad(state: Boolean) {
            _loading.value = state
    }

    fun setError(state: Throwable) {
        _throwable.value = state
    }

    val compositeDisposable = CompositeDisposable()

    fun <X> applySchedulers(): ObservableTransformer<X, X> {
        return ObservableTransformer { up ->
            up.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }

    fun add(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}