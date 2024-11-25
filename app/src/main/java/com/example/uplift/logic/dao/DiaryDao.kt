package com.example.uplift.logic.dao

import androidx.constraintlayout.helper.widget.Flow
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.uplift.data.models.Diary


@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: Diary)

    @Update
    suspend fun updateDiary(diary: Diary)

    @Delete
    suspend fun  deleteDiary(diary: Diary)


    @Query("SELECT * FROM diary ORDER BY diary_id DESC")
    fun getAllDiaries(): LiveData<List<Diary>>

    @Query("SELECT * FROM diary WHERE title LIKE :query OR content LIKE :query")
    fun searchDiary(query: String?): LiveData<List<Diary>>


    @Query("SELECT * FROM diary WHERE diary_id = :id")
    fun getDiaryById(id: Int): LiveData<Diary>


}