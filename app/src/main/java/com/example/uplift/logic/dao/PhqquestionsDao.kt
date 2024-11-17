package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Phqquestions

interface PhqquestionsDao {
    @Insert
    suspend fun insertAll(listPhqQuestions: List<Phqquestions>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestions(phqQuestions: Phqquestions)

    @Update
    suspend fun updateQuestions(phqQuestions: List<Phqquestions>)

    @Query("SELECT * FROM phqquestions ORDER BY question_order ASC")
    fun getAllQuestions(): LiveData<List<Phqquestions>>

    @Delete
    suspend fun deleteQuestions(phqQuestions: Phqquestions)

    @Query("SELECT * FROM phqquestions ORDER BY question_id DESC")
    fun deleteQuestions(): LiveData<List<Phqquestions>>

    @Query("DELETE FROM phqquestions")
    suspend fun deleteAllQuestions()
}