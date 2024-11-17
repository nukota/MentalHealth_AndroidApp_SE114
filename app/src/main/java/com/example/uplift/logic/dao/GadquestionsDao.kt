package com.example.uplift.logic.dao
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Gadquestions

interface GadquestionsDao {
    @Insert
    suspend fun insertAll(listGadQuestions: List<Gadquestions>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestions(gadQuestions: Gadquestions)

    @Update
    suspend fun updateQuestions(gadQuestions: List<Gadquestions>)

    @Query("SELECT * FROM gadquestions ORDER BY question_order ASC")
    fun getAllQuestions(): LiveData<List<Gadquestions>>

    @Delete
    suspend fun deleteQuestions(gadQuestions: Gadquestions)

    @Query("SELECT * FROM gadquestions ORDER BY question_id DESC")
    fun deleteQuestions(): LiveData<List<Gadquestions>>

    @Query("DELETE FROM gadquestions")
    suspend fun deleteAllQuestions()
}