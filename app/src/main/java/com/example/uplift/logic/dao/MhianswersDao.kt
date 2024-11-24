package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Mhianswers

interface MhianswersDao {
    @Query("SELECT * FROM mhianswers ORDER BY answer_id ASC")
    fun getAllAnswers(): LiveData<List<Mhianswers>>
}