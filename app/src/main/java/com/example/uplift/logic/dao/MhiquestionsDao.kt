package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Mhiquestions

interface MhiquestionsDao {
    @Insert
    suspend fun insertAll(listMhiQuestions: List<Mhiquestions>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestions(mhiQuestions: Mhiquestions)

    @Update
    suspend fun updateQuestions(mhiQuestions: List<Mhiquestions>)

    @Query("SELECT * FROM mhiquestions ORDER BY question_order ASC")
    fun getAllQuestions(): LiveData<List<Mhiquestions>>

    @Delete
    suspend fun deleteQuestions(mhiQuestions: Mhiquestions)

    @Query("SELECT * FROM mhiquestions ORDER BY question_id DESC")
    fun deleteQuestions(): LiveData<List<Mhiquestions>>

    @Query("DELETE FROM mhiquestions")
    suspend fun deleteAllQuestions()
}