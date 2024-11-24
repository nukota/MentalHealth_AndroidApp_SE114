package com.example.uplift.logic.dao
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.uplift.data.models.Gadquestions

interface GadquestionsDao {
    @Query("SELECT * FROM gadquestions ORDER BY question_id ASC")
    fun getAllQuestions(): LiveData<List<Gadquestions>>
}