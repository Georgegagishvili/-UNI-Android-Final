package com.example.finalexam.services.api

import com.example.finalexam.models.Question
import retrofit2.Call
import retrofit2.http.GET

interface QuizService {
    @GET("questions")
    fun getQuestions(
    ): Call<ArrayList<Question>>

}