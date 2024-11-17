package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.logic.dao.MhiquestionsDao

class MhiRepository(private val mhiQuestionsDao: MhiquestionsDao) {
    fun getAllQuestions(): LiveData<List<Mhiquestions>> {
        return mhiQuestionsDao.getAllQuestions()
    }
    suspend fun insertAll(listMhiQuestions: List<Mhiquestions>) {
        mhiQuestionsDao.insertAll(listMhiQuestions)
    }
    suspend fun addQuestions(mhiQuestions: Mhiquestions) {
        mhiQuestionsDao.addQuestions(mhiQuestions)
    }
    suspend fun updateQuestions(mhiQuestions: List<Mhiquestions>) {
        mhiQuestionsDao.updateQuestions(mhiQuestions)
    }
    suspend fun deleteQuestions(mhiQuestions: Mhiquestions) {
        mhiQuestionsDao.deleteQuestions(mhiQuestions)
    }
    suspend fun deleteAllQuestions() {
        mhiQuestionsDao.deleteAllQuestions()
    }
}