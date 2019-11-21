package com.eb.event365kotlin.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.eb.event365kotlin.R
import com.eb.event365kotlin.common.utils.SharedPrefWrapper
import com.eb.event365kotlin.common.extensions.hide
import com.eb.event365kotlin.common.extensions.show
import com.eb.event365kotlin.common.extensions.text
import com.eb.event365kotlin.common.extensions.toast
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject


class Home : AppCompatActivity() {

    private val viewModel:HomeViewModel by inject()

    private val sharedPrefWrapper: SharedPrefWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.loadPosts()

        viewModel.loading.observe(this, Observer { if(it)pBar.show() else pBar.hide()})
        viewModel.authError.observe(this, Observer { if(it) toast { text { "Your session has been expired!" }}})
        viewModel.error.observe(this, Observer { toast { text { "Something went wrong!" }}})


    }
}
