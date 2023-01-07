package com.example.finalexam.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SampleModels")
data class SampleModel(
    @PrimaryKey(autoGenerate = true) val id : Int?
) {
    companion object {
        val dummyData: ArrayList<SampleModel> = arrayListOf()
    }
}
