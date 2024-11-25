package com.example.uplift.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.models.Diary
import com.example.uplift.logic.repository.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryModel(application: Application, private val diaryRepository: DiaryRepository ) : AndroidViewModel(application) {


    fun addDairy(diary: Diary) =
        viewModelScope.launch{
            diaryRepository.insertDiary(diary)
        }


    fun updateDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.updateDiary(diary)
        }
    }

    fun deleteDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.deleteDiary(diary)
        }
    }

    fun getAllDiaries() = diaryRepository.getAllDiaries()


    fun seachDiary(query: String?) =
        diaryRepository.searchDiary(query)

    fun getDiaryById(id: Int): LiveData<Diary> = diaryRepository.getDiaryById(id)
}