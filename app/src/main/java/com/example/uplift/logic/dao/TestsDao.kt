package com.example.uplift.logic.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.uplift.data.models.Tests

@Dao
interface TestsDao {
    @Insert
    suspend fun insert(test: Tests)

    @Query("SELECT * FROM tests")
    suspend fun getAllTests(): List<Tests>

    @Query("SELECT * FROM tests WHERE test_id = :testId")
    suspend fun getTestById(testId: Int): Tests?

    @Query("DELETE FROM tests")
    suspend fun deleteAllTests()
}