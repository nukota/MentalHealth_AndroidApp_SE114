package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Phqquestions

interface PhqquestionsDao {
    suspend fun insertQuestion(phqQuestions: Phqquestions)

    @Update
    suspend fun updateQuestions(phqQuestions: List<Phqquestions>)

    @Query("SELECT * FROM phqquestions ORDER BY question_id ASC")
    fun getAllQuestions(): LiveData<List<Phqquestions>>

    @Query("SELECT * FROM phqquestions WHERE question_id = :questionId")
    suspend fun getQuestionById(questionId: Int): Phqquestions?

    @Query("DELETE FROM phqquestions WHERE question_id=:questionId")
    suspend fun deleteQuestionById(questionId:Int)

    @Query("DELETE FROM gadquestions")
    suspend fun deleteAllQuestions()
}