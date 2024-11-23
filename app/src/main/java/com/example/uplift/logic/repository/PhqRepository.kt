package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Phqquestions
import com.example.uplift.logic.dao.PhqquestionsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhqRepository(private val phqQuestionsDao: PhqquestionsDao) {
    fun getAllQuestions(): LiveData<List<Phqquestions>> {
        return phqQuestionsDao.getAllQuestions()
    }
    suspend fun getQuestionById(questionId: Int): Phqquestions? {
        return withContext(Dispatchers.IO) {
            phqQuestionsDao.getQuestionById(questionId)
        }
    }
    suspend fun insertQuestion(phqQuestions: Phqquestions) {
        withContext(Dispatchers.IO) {
            phqQuestionsDao.insertQuestion(phqQuestions)
        }
    }
    suspend fun updateQuestions(phqQuestionsList: List<Phqquestions>) {
        withContext(Dispatchers.IO) {
            phqQuestionsDao.updateQuestions(phqQuestionsList)
        }
    }
    suspend fun deleteAllQuestions() {
        withContext(Dispatchers.IO) {
            phqQuestionsDao.deleteAllQuestions()
        }
    }
    suspend fun deleteQuestionById(questionId: Int){
        withContext(Dispatchers.IO) {
            phqQuestionsDao.deleteQuestionById(questionId)
        }
    }
}