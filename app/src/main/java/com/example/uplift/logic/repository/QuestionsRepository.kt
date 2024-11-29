package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.database.QuestionsDatabase
import com.example.uplift.data.models.Answer
import com.example.uplift.data.models.Questions

class QuestionsRepository(private val database: QuestionsDatabase)  {
    private val questionsDao = database.questionsDao()
    private val answersDao = database.answersDao()

    // Questions
    fun getAllQuestions(): LiveData<List<Questions>> {
        return questionsDao.getAllQuestions()
    }

    // Answers
    fun getAllAnswers(): LiveData<List<Answer>> {
        return answersDao.getAllAnswers()
    }
}