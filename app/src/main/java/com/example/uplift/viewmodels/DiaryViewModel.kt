package com.example.uplift.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Diary
import com.example.uplift.data.repository.DiaryRepository

class DiaryViewModel : ViewModel() {
    private val repository = DiaryRepository()
    val diaries: LiveData<List<Diary>> = repository.diaries

    fun getDiaryById(diaryId: Int): LiveData<Diary?> {
        return repository.getDiaryById(diaryId)
    }
}