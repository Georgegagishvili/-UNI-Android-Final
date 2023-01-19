package com.example.finalexam.db

import androidx.room.*

@Dao
interface ResultDao {
    @Query("SELECT * FROM RESULTS")
    fun selectAll(): List<Result>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg resultModel: Result)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg resultModel: Result)

    @Delete
    fun delete(resultModel: Result)
}