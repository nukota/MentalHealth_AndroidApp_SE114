package com.example.uplift.logic.dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Gadquestions

interface GadquestionsDao {
    @Insert
    suspend fun insertQuestion(gadQuestions: Gadquestions)

    @Update
    suspend fun updateQuestions(gadQuestions: List<Gadquestions>)

    @Query("SELECT * FROM gadquestions ORDER BY question_id ASC")
    fun getAllQuestions(): LiveData<List<Gadquestions>>

    @Query("SELECT * FROM gadquestions WHERE question_id = :questionId")
    suspend fun getQuestionById(questionId: Int): Gadquestions?

    @Query("DELETE FROM gadquestions WHERE question_id=:questionId")
    suspend fun deleteQuestionById(questionId:Int)

    @Query("DELETE FROM gadquestions")
    suspend fun deleteAllQuestions()
}