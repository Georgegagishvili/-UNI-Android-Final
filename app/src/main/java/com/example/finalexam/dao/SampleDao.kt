package com.example.finalexam.dao

import androidx.room.*
import com.example.finalexam.models.SampleModel

@Dao
interface SampleDao {
    @Query("SELECT * FROM SampleModels")
    fun getAll(): List<SampleModel>

    @Query("SELECT * FROM SampleModels WHERE id == (:id)")
    fun getById(id: Int): SampleModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: SampleModel)

    @Query("DELETE FROM SampleModels")
    fun deleteEverything()

    @Delete
    fun deleteItemById(game : SampleModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: SampleModel)

}