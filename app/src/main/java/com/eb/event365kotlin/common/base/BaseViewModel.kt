package com.eb.event365kotlin.common.base


import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

abstract class BaseViewModel ()  : ViewModel(){

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    private val _throwable : MutableLiveData<Throwable> = MutableLiveData()
    private val _authError : MutableLiveData<Boolean> = MutableLiveData()



    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _throwable
    val authError: LiveData<Boolean> get() = _authError

    fun setLoad(state: Boolean) {
            _loading.value = state
    }

    fun setError(state: Throwable) {
        if (state is HttpException && state.code()==401)
            _authError.postValue(true)
        else
            _throwable.value = state
    }

    val compositeDisposable = CompositeDisposable()


    fun add(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}