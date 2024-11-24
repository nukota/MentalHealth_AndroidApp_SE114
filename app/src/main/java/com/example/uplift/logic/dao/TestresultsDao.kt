package com.example.uplift.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Testresults
import com.example.uplift.data.models.Tests

interface TestresultsDao {
    @Insert
    suspend fun insertTestresults(testresults: Testresults)

    @Update
    suspend fun updateTestresults(testresults: List<Testresults>)

    @Query("SELECT * FROM testresults ORDER BY test_id ASC")
    fun getAllTestresults(): LiveData<List<Testresults>>

    @Query("SELECT * FROM testresults WHERE test_id = :testId")
    suspend fun getTestresultsById(testId: Int): Testresults?

    @Query("DELETE FROM testresults WHERE test_id = :testId")
    suspend fun deleteTestresultsById(testId: Int)

    @Query("DELETE FROM testresults")
    suspend fun deleteAllTestresults()
}