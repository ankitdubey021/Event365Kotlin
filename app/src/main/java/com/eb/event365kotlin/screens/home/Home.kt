package com.eb.event365kotlin.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eb.event365kotlin.R
import com.eb.event365kotlin.common.ApiService
import com.eb.event365kotlin.common.extensions.*
import com.eb.event365kotlin.databinding.ActivityHomeBinding
import com.eb.event365kotlin.screens.SecondActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext


class Home : AppCompatActivity() {

    private lateinit var viewModel:HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    private val apiService:ApiService by inject()

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_home);


        viewModelFactory= HomeViewModelFactory(apiService)
        viewModel=ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java)

        binding.viewmodel=viewModel
        binding.lifecycleOwner=this

        viewModel.loading.observe(this, Observer { if(it)pBar.show() else pBar.hide()})
        viewModel.authError.observe(this, Observer { if(it) toast { text { "Your session has been expired!" }}})
        viewModel.error.observe(this, Observer { if(it!=null)toast { text { "Something went wrong!" }}})

    }

}
