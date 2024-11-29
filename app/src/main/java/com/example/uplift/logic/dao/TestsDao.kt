package com.example.uplift.logic.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Tests

@Dao
interface TestsDao {
    @Insert
    suspend fun insertTest(test: Tests)

    @Update
    suspend fun updateTests(test: List<Tests>)

    @Query("SELECT * FROM tests ORDER BY test_id ASC")
    fun getAllTests(): LiveData<List<Tests>>

    @Query("SELECT * FROM tests WHERE test_id = :testId")
    suspend fun getTestById(testId: Int): Tests?

    @Query("DELETE FROM tests WHERE test_id = :testId")
    suspend fun deleteTestById(testId: Int)

    @Query("DELETE FROM tests")
    suspend fun deleteAllTests()
}