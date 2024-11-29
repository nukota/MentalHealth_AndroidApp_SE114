package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Answer

interface AnswersDao {
    @Query("SELECT * FROM answers")
    fun getAllAnswers(): LiveData<List<Answer>>

}