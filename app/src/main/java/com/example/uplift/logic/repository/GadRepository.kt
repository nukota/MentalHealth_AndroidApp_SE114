package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.database.GadDatabase
import com.example.uplift.data.models.Gadanswers
import com.example.uplift.data.models.Gadquestions

class GadRepository(private val database: GadDatabase)  {
    private val gadquestionsDao = database.gadQuestionsDao()
    private val gadanswersDao = database.gadAnswersDao()

    // Questions
    fun getAllQuestions(): LiveData<List<Gadquestions>> {
        return gadquestionsDao.getAllQuestions()
    }

    // Answers
    fun getAllAnswers(): LiveData<List<Gadanswers>> {
        return gadanswersDao.getAllAnswers()
    }
}