package com.eb.event365kotlin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.eb.event365kotlin.R
import com.eb.event365kotlin.common.utils.SharedPrefWrapper
import com.eb.event365kotlin.common.extensions.hide
import com.eb.event365kotlin.common.extensions.show
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class Home : AppCompatActivity() {

    private val viewModel:HomeViewModel by inject()

    private val sharedPrefWrapper: SharedPrefWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.loading.observe(this, Observer { if(it)pBar.show() else pBar.hide()})

        viewModel.loadPosts().observe(this, Observer {
            Log.e("Hurrah",it.toString())
        })
    }
}
