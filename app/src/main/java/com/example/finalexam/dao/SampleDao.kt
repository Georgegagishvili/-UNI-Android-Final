package com.example.finalexam.dao

import androidx.room.*
import com.example.finalexam.models.Question

@Dao
interface SampleDao {
    @Query("SELECT * FROM SampleModels")
    fun getAll(): List<Question>

    @Query("SELECT * FROM SampleModels WHERE id == (:id)")
    fun getById(id: Int): Question

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Question)

    @Query("DELETE FROM SampleModels")
    fun deleteEverything()

    @Delete
    fun deleteItemById(game : Question)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: Question)

}