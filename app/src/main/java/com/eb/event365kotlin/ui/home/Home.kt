package com.eb.event365kotlin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.eb.event365kotlin.R
import com.eb.event365kotlin.common.utils.SharedPrefWrapper
import com.eb.event365kotlin.common.extensions.hide
import com.eb.event365kotlin.common.extensions.show
import com.eb.event365kotlin.common.extensions.text
import com.eb.event365kotlin.common.extensions.toast
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import retrofit2.HttpException

class Home : AppCompatActivity() {

    private val viewModel:HomeViewModel by inject()

    private val sharedPrefWrapper: SharedPrefWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.loading.observe(this, Observer { if(it)pBar.show() else pBar.hide()})
        viewModel.error.observe(this, Observer {
            if (it is HttpException) {
                when(it.code()){
                    400 -> Log.d("", "onError: BAD REQUEST")
                    401 -> toast { text { "Unauthorized Access" } }
                    403 -> Log.d("", "onError: FORBIDDEN")
                    404 -> Log.d("", "onError: NOT FOUND")
                    405 -> Log.d("", "onError: INTERNAL SERVER ERROR")
                    406 -> Log.d("", "onError: BAD GATEWAY")
                    else -> toast { text { "Something went wrong!" } }
                }
            }
        })

        viewModel.loadPosts()
    }
}
