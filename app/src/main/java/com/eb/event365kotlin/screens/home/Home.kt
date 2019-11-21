package com.eb.event365kotlin.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.eb.event365kotlin.R
import com.eb.event365kotlin.common.extensions.hide
import com.eb.event365kotlin.common.extensions.show
import com.eb.event365kotlin.common.extensions.text
import com.eb.event365kotlin.common.extensions.toast
import com.eb.event365kotlin.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject


class Home : AppCompatActivity() {

    private val viewModel:HomeViewModel by inject()
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_home);

        binding.viewmodel=viewModel
        binding.lifecycleOwner=this

        viewModel.loading.observe(this, Observer { if(it)pBar.show() else pBar.hide()})
        viewModel.authError.observe(this, Observer { if(it) toast { text { "Your session has been expired!" }}})
        viewModel.error.observe(this, Observer { toast { text { "Something went wrong!" }}})

    }
}
