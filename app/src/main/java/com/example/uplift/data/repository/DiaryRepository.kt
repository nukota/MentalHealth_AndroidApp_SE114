package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Diary
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DiaryRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("diaries")

    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> get() = _diaries

    init {
        fetchDiaries()
    }

    fun getDiaryById(diaryId: Int): LiveData<Diary?> {
        val diary = _diaries.value?.find { it.diary_id == diaryId }
        val liveData = MutableLiveData<Diary?>()
        liveData.value = diary
        return liveData
    }

    private fun fetchDiaries() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val diaryList = mutableListOf<Diary>()
                for (diarySnapshot in snapshot.children) {
                    val diary = diarySnapshot.getValue(Diary::class.java)
                    if (diary != null) {
                        diaryList.add(diary)
                    }
                }
                _diaries.value = diaryList
                Log.d("DiaryRepository", "Fetched diaries")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DiaryRepository", "Error fetching diaries", error.toException())
            }
        })
    }
}