package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Testresults
import com.example.uplift.data.models.Tests
import com.example.uplift.logic.dao.TestresultsDao
import com.example.uplift.logic.dao.TestsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestresultsRepository(private val testresultsDao: TestresultsDao) {
    fun getAllTestresults(): LiveData<List<Testresults>> {
        return testresultsDao.getAllTestresults()
    }
    suspend fun getTestresultsById(testId: Int): Testresults? {
        return withContext(Dispatchers.IO) {
            testresultsDao.getTestresultsById(testId)
        }
    }
    suspend fun insertTestresults(test: Testresults) {
        withContext(Dispatchers.IO) {
            testresultsDao.insertTestresults(test)
        }
    }
    suspend fun updateTestresults(testList: List<Testresults>) {
        withContext(Dispatchers.IO) {
            testresultsDao.updateTestresults(testList)
        }
    }
    suspend fun deleteAllTestresults(){
        withContext(Dispatchers.IO) {
            testresultsDao.deleteAllTestresults()
        }
    }
    suspend fun deleteTestresultsById(testId: Int){
        withContext(Dispatchers.IO) {
            testresultsDao.deleteTestresultsById(testId)
        }
    }
}