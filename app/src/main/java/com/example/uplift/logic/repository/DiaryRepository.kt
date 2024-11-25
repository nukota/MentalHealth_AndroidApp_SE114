package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import androidx.room.util.query
import com.example.uplift.data.database.DiaryDatabase
import com.example.uplift.data.models.Diary
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.toObject


class DiaryRepository(private val db: DiaryDatabase) {

    private val firestore: FirebaseFirestore = Firebase.firestore

    suspend fun insertDiary(diary: Diary) {
    db.diaryDao().insertDiary(diary)
    }

    suspend fun deleteDiary(diary: Diary) {
        db.diaryDao().deleteDiary(diary)

    }


    suspend fun updateDiary(diary: Diary) {
        db.diaryDao().updateDiary(diary)
    }

    fun getAllDiaries() = db.diaryDao().getAllDiaries()
    fun searchDiary(query: String?) = db.diaryDao().searchDiary(query)

    fun getDiaryById(id: Int): LiveData<Diary> = db.diaryDao().getDiaryById(id)

}