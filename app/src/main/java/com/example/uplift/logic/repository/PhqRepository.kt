package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.data.models.Phqquestions
import com.example.uplift.logic.dao.PhqquestionsDao

class PhqRepository(private val phqQuestionsDao: PhqquestionsDao) {
    fun getAllQuestions(): LiveData<List<Phqquestions>> {
        return phqQuestionsDao.getAllQuestions()
    }
    suspend fun insertAll(listPhqQuestions: List<Phqquestions>) {
        phqQuestionsDao.insertAll(listPhqQuestions)
    }
    suspend fun addQuestions(phqQuestions: Phqquestions) {
        phqQuestionsDao.addQuestions(phqQuestions)
    }
    suspend fun updateQuestions(phqQuestions: List<Phqquestions>) {
        phqQuestionsDao.updateQuestions(phqQuestions)
    }
    suspend fun deleteQuestions(phqQuestions: Phqquestions) {
        phqQuestionsDao.deleteQuestions(phqQuestions)
    }
    suspend fun deleteAllQuestions() {
        phqQuestionsDao.deleteAllQuestions()
    }
}