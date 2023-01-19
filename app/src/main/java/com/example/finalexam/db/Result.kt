package com.example.finalexam.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RESULTS")
class Result(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Long,

    @ColumnInfo(name = "USER")
    val user: String,

    @ColumnInfo(name = "SCORE")
    val score: Int


)
