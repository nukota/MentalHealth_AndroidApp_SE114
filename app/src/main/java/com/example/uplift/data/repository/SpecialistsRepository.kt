package com.example.uplift.data.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Specialists
import com.example.uplift.logic.dao.SpecialistsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpecialistsRepository(private val specialistsDao: SpecialistsDao) {
    fun getAllSpecialists(): LiveData<List<Specialists>> {
        return specialistsDao.getAllSpecialists()
    }
    suspend fun getSpecialistById(testId: Int): Specialists? {
        return withContext(Dispatchers.IO) {
            specialistsDao.getSpecialistById(testId)
        }
    }
    suspend fun insertSpecialist(specialists: Specialists) {
        withContext(Dispatchers.IO) {
            specialistsDao.insertSpecialist(specialists)
        }
    }
    suspend fun updateSpecialists(specialistsList: List<Specialists>) {
        withContext(Dispatchers.IO) {
            specialistsDao.updateSpecialists(specialistsList)
        }
    }
    suspend fun deleteAllSpecialists() {
        withContext(Dispatchers.IO) {
            specialistsDao.deleteAllSpecialists()
        }
    }
    suspend fun deleteSpecialistById(testId: Int){
        withContext(Dispatchers.IO) {
            specialistsDao.deleteSpecialistById(testId)
        }
    }
}