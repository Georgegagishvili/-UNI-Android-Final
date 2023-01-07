package com.example.finalexam.services.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit
    val reqResService: ApiService get() = retrofit.create(ApiService::class.java)

    fun getRetrofit(): Retrofit {
        okHttpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}