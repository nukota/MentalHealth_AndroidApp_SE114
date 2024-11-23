package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.Tests
import com.example.uplift.data.models.User
import com.example.uplift.logic.dao.TestsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestsRepository(private val testsDao: TestsDao) {
    fun getAllTests(): LiveData<List<Tests>> {
        return testsDao.getAllTests()
    }
    suspend fun getTestById(testId: Int): Tests? {
        return withContext(Dispatchers.IO) {
            testsDao.getTestById(testId)
        }
    }
    suspend fun insertTest(test: Tests) {
        withContext(Dispatchers.IO) {
            testsDao.insertTest(test)
        }
    }
    suspend fun updateTests(testList: List<Tests>) {
        withContext(Dispatchers.IO) {
            testsDao.updateTests(testList)
        }
    }
    suspend fun deleteAllTests() {
        withContext(Dispatchers.IO) {
            testsDao.deleteAllTests()
        }
    }
    suspend fun deleteTestById(testId: Int){
        withContext(Dispatchers.IO) {
            testsDao.deleteTestById(testId)
        }
    }
}