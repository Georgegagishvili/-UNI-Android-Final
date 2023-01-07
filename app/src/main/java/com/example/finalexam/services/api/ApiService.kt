package com.example.finalexam.services.api

import com.example.finalexam.models.SampleData
import com.example.finalexam.models.SampleModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("sample")
    fun getSample(
        @Query("sample") sample: Int,
    ): Call<SampleData<List<SampleModel>>>

    @GET("sample/{id}")
    fun getSingle(@Path("id") id: Long): Call<SampleData<SampleModel>>
}