package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Mhiquestions

interface MhiquestionsDao {
    @Insert
    suspend fun insertQuestion(mhiQuestions: Mhiquestions)

    @Update
    suspend fun updateQuestions(mhiQuestions: List<Mhiquestions>)

    @Query("SELECT * FROM mhiquestions ORDER BY question_id ASC")
    fun getAllQuestions(): LiveData<List<Mhiquestions>>

    @Query("SELECT * FROM mhiquestions WHERE question_id = :questionId")
    suspend fun getQuestionById(questionId: Int): Mhiquestions?

    @Query("DELETE FROM mhiquestions WHERE question_id=:questionId")
    suspend fun deleteQuestionById(questionId:Int)

    @Query("DELETE FROM mhiquestions")
    suspend fun deleteAllQuestions()
}