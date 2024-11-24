package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.database.MhiDatabase
import com.example.uplift.data.models.Mhianswers
import com.example.uplift.data.models.Mhiquestions

class MhiRepository(private val database: MhiDatabase) {
    private val mhiquestionsDao = database.mhiQuestionsDao()
    private val mhianswersDao = database.mhiAnswersDao()

    // Questions
    fun getAllQuestions(): LiveData<List<Mhiquestions>> {
        return mhiquestionsDao.getAllQuestions()
    }

    // Answers
    fun getAllAnswers(): LiveData<List<Mhianswers>> {
        return mhianswersDao.getAllAnswers()
    }
}