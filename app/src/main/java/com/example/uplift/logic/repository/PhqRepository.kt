package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.database.PhqDatabase
import com.example.uplift.data.models.Gadanswers
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.data.models.Phqanswers
import com.example.uplift.data.models.Phqquestions
import com.example.uplift.logic.dao.PhqquestionsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhqRepository(private val database: PhqDatabase) {
    private val phqQuestionsDao = database.phqQuestionsDao()
    private val phqAnswersDao = database.phqAnswersDao()

    // Questions
    fun getAllQuestions(): LiveData<List<Phqquestions>> {
        return phqQuestionsDao.getAllQuestions()
    }

    // Answers
    fun getAllAnswers(): LiveData<List<Phqanswers>> {
        return phqAnswersDao.getAllAnswers()
    }
}