package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Gadanswers

interface GadanswersDao {
    @Query("SELECT * FROM gadanswers ORDER BY answer_id ASC")
    fun getAllAnswers(): LiveData<List<Gadanswers>>

}