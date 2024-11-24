package com.example.uplift.logic.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Specialists

@Dao
interface SpecialistsDao {
    @Insert
    suspend fun insertSpecialist(specialists: Specialists)

    @Update
    suspend fun updateSpecialists(test: List<Specialists>)

    @Query("SELECT * FROM specialists ORDER BY specialist_id ASC")
    fun getAllSpecialists(): LiveData<List<Specialists>>

    @Query("SELECT * FROM specialists WHERE specialist_id = :specialistId")
    suspend fun getSpecialistById(specialistId: Int): Specialists?

    @Query("DELETE FROM specialists WHERE specialist_id = :specialistId")
    suspend fun deleteSpecialistById(specialistId: Int)

    @Query("DELETE FROM specialists")
    suspend fun deleteAllSpecialists()
}