package com.e.bookstory.model

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        private val retrofit =
            Retrofit.Builder()
                .baseUrl("http://109.123.152.98:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(Service::class.java)
    }

    override fun onCreate() {
        super.onCreate()

    }



}