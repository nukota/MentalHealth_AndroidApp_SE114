package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Phqanswers

interface PhqanswersDao {
    @Query("SELECT * FROM phqanswers ORDER BY answer_id ASC")
    fun getAllAnswers(): LiveData<List<Phqanswers>>
}