package com.example.uplift.logic.dao
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Questions

interface QuestionsDao {
    @Query("SELECT * FROM questions")
    fun getAllQuestions(): LiveData<List<Questions>>
}