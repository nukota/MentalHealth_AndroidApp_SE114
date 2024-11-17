package com.example.uplift.logic.repository

import com.example.uplift.data.models.Tests
import com.example.uplift.logic.dao.TestsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestsRepository(private val testsDao: TestsDao) {
    suspend fun getAllTests(): List<Tests> {
        return withContext(Dispatchers.IO) {
            testsDao.getAllTests()
        }
    }
    suspend fun getTestById(testId: Int): Tests? {
        return withContext(Dispatchers.IO) {
            testsDao.getTestById(testId)
        }
    }
    suspend fun insertTest(test: Tests) {
        withContext(Dispatchers.IO) {
            testsDao.insert(test)
        }
    }
    suspend fun deleteAllTests() {
        withContext(Dispatchers.IO) {
            testsDao.deleteAllTests()
        }
    }
}