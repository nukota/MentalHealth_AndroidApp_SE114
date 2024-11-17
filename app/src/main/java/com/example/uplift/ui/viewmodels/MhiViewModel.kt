package com.example.uplift.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.database.MhiDatabase
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.logic.repository.MhiRepository
import kotlinx.coroutines.launch

class MhiViewModel(application: Application) : AndroidViewModel(application) {
    private val mhiRepository: MhiRepository
    private val allQuestions: LiveData<List<Mhiquestions>>

    init {
        val mhiQuestionsDao = MhiDatabase.getDatabase(application).mhiQuestionsDao()
        mhiRepository = MhiRepository(mhiQuestionsDao)
        allQuestions = mhiRepository.getAllQuestions()
    }

    fun insertAll(listMhiQuestions: List<Mhiquestions>) {
        viewModelScope.launch {
            mhiRepository.insertAll(listMhiQuestions)
        }
    }
    fun addQuestions(mhiQuestions: Mhiquestions) {
        viewModelScope.launch {
            mhiRepository.addQuestions(mhiQuestions)
        }
    }
    fun updateQuestions(mhiQuestions: List<Mhiquestions>) {
        viewModelScope.launch {
            mhiRepository.updateQuestions(mhiQuestions)
        }
    }
    fun deleteQuestions(mhiQuestions: Mhiquestions) {
        viewModelScope.launch {
            mhiRepository.deleteQuestions(mhiQuestions)
        }
    }
    fun deleteAllQuestions() {
        viewModelScope.launch {
            mhiRepository.deleteAllQuestions()
        }
    }
}