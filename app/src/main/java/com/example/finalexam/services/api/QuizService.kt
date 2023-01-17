package com.example.finalexam.services.api

import com.example.finalexam.models.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {
    @GET("questions")
    fun getQuestions(
        @Query("categories") category: String,
    ): Call<ArrayList<Question>>

}