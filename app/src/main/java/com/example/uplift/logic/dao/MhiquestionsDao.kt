package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Mhiquestions

interface MhiquestionsDao {
    @Query("SELECT * FROM mhiquestions ORDER BY question_id ASC")
    fun getAllQuestions(): LiveData<List<Mhiquestions>>
}