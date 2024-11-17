package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.data.models.User
import com.example.uplift.logic.dao.GadquestionsDao

class GadRepository(private val gadQuestionsDao: GadquestionsDao)  {
    fun getAllQuestions(): LiveData<List<Gadquestions>> {
        return gadQuestionsDao.getAllQuestions()
    }
    suspend fun insertAll(listGadQuestions: List<Gadquestions>) {
        gadQuestionsDao.insertAll(listGadQuestions)
    }
    suspend fun addQuestions(gadQuestions: Gadquestions) {
        gadQuestionsDao.addQuestions(gadQuestions)
    }
    suspend fun updateQuestions(gadQuestions: List<Gadquestions>) {
        gadQuestionsDao.updateQuestions(gadQuestions)
    }
    suspend fun deleteQuestions(gadQuestions: Gadquestions) {
        gadQuestionsDao.deleteQuestions(gadQuestions)
    }
    suspend fun deleteAllQuestions() {
        gadQuestionsDao.deleteAllQuestions()
    }
}