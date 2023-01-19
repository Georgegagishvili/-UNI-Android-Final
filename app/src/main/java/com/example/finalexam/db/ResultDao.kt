package com.example.finalexam.db

import androidx.room.*

@Dao
interface ResultDao {
    @Query("SELECT * FROM RESULTS")
    fun selectAll(): List<Result>

    @Query("SELECT * From RESULTS WHERE user == (:user)")
    fun selectByUser(user: String): Result

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg resultModel: Result)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg resultModel: Result)

    @Delete
    suspend fun delete(resultModel: Result)
}