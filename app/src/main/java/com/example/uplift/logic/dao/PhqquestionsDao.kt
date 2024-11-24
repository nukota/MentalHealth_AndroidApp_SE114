package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Phqquestions

interface PhqquestionsDao {
    @Query("SELECT * FROM phqquestions ORDER BY question_id ASC")
    fun getAllQuestions(): LiveData<List<Phqquestions>>
}