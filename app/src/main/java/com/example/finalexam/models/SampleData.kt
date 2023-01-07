package com.example.finalexam.models

import com.google.gson.annotations.SerializedName

data class SampleData<T>(
    @SerializedName("Sample")
    val sample: Int,
)
