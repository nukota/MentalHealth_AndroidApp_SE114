package com.example.uplift.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uplift.logic.repository.DiaryRepository

class DiaryViewModelFactory(val app: Application, private val diaryRepository: DiaryRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DiaryModel(app, diaryRepository) as T
    }

}