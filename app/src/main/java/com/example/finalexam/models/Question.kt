package com.example.finalexam.models

import com.google.gson.annotations.SerializedName


data class Question(
    @SerializedName("id")
    val id: String?,
    @SerializedName("question")
    val question : String,
    @SerializedName("category")
    val category: String,
    @SerializedName("correctAnswer")
    val correctAnswer: String,
    @SerializedName("incorrectAnswers")
    val incorrectAnswers: ArrayList<String>,
    @SerializedName("difficulty")
    val difficulty: String,
) {
    companion object {
        val dummyData: ArrayList<Question> = arrayListOf()
    }
}
