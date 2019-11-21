package com.eb.event365kotlin.common

import android.content.Context
import com.eb.event365kotlin.common.schedulers.SchedulerProvider
import com.eb.event365kotlin.common.utils.BASE_URL
import com.eb.event365kotlin.common.utils.SharedPrefWrapper
import com.eb.event365kotlin.repository.HomeRepository
import com.eb.event365kotlin.screens.home.HomeViewModel


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val applicationModules = module {

    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    single { createOkHttpClient() }
    single { androidApplication().getSharedPreferences("event365kotlin", Context.MODE_PRIVATE) }
    single { SharedPrefWrapper(get()) }

    single { SchedulerProvider() }

    single<ApiService> {
        createWebService(
            get(),
            get(),
            BASE_URL
        )
    }

    factory { HomeRepository(get()) }
    viewModel { HomeViewModel(get(),get()) }
}


fun createOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(logging)
        .build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    url: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        //.addConverterFactory(MoshiConverterFactory.create(moshi))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

