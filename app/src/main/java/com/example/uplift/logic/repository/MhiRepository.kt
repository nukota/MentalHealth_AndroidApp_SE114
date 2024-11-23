package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.logic.dao.MhiquestionsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MhiRepository(private val mhiQuestionsDao: MhiquestionsDao) {
    fun getAllQuestions(): LiveData<List<Mhiquestions>> {
        return mhiQuestionsDao.getAllQuestions()
    }
    suspend fun getQuestionById(questionId: Int): Mhiquestions? {
        return withContext(Dispatchers.IO) {
            mhiQuestionsDao.getQuestionById(questionId)
        }
    }
    suspend fun insertQuestion(mhiQuestions: Mhiquestions) {
        withContext(Dispatchers.IO) {
            mhiQuestionsDao.insertQuestion(mhiQuestions)
        }
    }
    suspend fun updateQuestions(gadQuestionsList: List<Mhiquestions>) {
        withContext(Dispatchers.IO) {
            mhiQuestionsDao.updateQuestions(gadQuestionsList)
        }
    }
    suspend fun deleteAllQuestions() {
        withContext(Dispatchers.IO) {
            mhiQuestionsDao.deleteAllQuestions()
        }
    }
    suspend fun deleteQuestionById(questionId: Int){
        withContext(Dispatchers.IO) {
            mhiQuestionsDao.deleteQuestionById(questionId)
        }
    }
}