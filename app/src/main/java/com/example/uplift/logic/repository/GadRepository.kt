package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.data.models.Tests
import com.example.uplift.data.models.User
import com.example.uplift.logic.dao.GadquestionsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GadRepository(private val gadQuestionsDao: GadquestionsDao)  {
    fun getAllQuestions(): LiveData<List<Gadquestions>> {
        return gadQuestionsDao.getAllQuestions()
    }
    suspend fun getQuestionById(questionId: Int): Gadquestions? {
        return withContext(Dispatchers.IO) {
            gadQuestionsDao.getQuestionById(questionId)
        }
    }
    suspend fun insertQuestion(gadQuestions: Gadquestions) {
        withContext(Dispatchers.IO) {
            gadQuestionsDao.insertQuestion(gadQuestions)
        }
    }
    suspend fun updateQuestions(gadQuestionsList: List<Gadquestions>) {
        withContext(Dispatchers.IO) {
            gadQuestionsDao.updateQuestions(gadQuestionsList)
        }
    }
    suspend fun deleteAllQuestions() {
        withContext(Dispatchers.IO) {
            gadQuestionsDao.deleteAllQuestions()
        }
    }
    suspend fun deleteQuestionById(questionId: Int){
        withContext(Dispatchers.IO) {
            gadQuestionsDao.deleteQuestionById(questionId)
        }
    }
}