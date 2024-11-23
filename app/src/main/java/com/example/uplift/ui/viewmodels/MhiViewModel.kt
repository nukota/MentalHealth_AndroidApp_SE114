package com.example.uplift.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.database.MhiDatabase
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.logic.repository.MhiRepository
import kotlinx.coroutines.launch

class MhiViewModel(application: Application) : AndroidViewModel(application) {
    private val mhiRepository: MhiRepository
    private val _allQuestions  = MutableLiveData<List<Mhiquestions>>()
    val allQuestions: LiveData<List<Mhiquestions>> get() = _allQuestions

    init {
        val mhiQuestionsDao = MhiDatabase.getDatabase(application).mhiQuestionsDao()
        mhiRepository = MhiRepository(mhiQuestionsDao)
        getAllQuestions()
    }
    private fun getAllQuestions() {
        viewModelScope.launch {
            mhiRepository.getAllQuestions().observeForever { mhiquestions ->
                _allQuestions.postValue(mhiquestions)
            }
        }
    }
    fun insertQuestion(mhiQuestions: Mhiquestions) {
        viewModelScope.launch {
            mhiRepository.insertQuestion(mhiQuestions)
            getAllQuestions()
        }
    }
    fun updateQuestions(mhiQuestionsList: List<Mhiquestions>) {
        viewModelScope.launch {
            mhiRepository.updateQuestions(mhiQuestionsList)
            getAllQuestions()
        }
    }
    fun deleteAllQuestions() {
        viewModelScope.launch {
            mhiRepository.deleteAllQuestions()
            _allQuestions.postValue(emptyList())
        }
    }
    fun deleteQuestionById(questionId:Int) {
        viewModelScope.launch {
            mhiRepository.deleteQuestionById(questionId)
            getAllQuestions()
        }
    }
    fun getQuestionById(questionId: Int){
        viewModelScope.launch {
            val test = mhiRepository.getQuestionById(questionId)
        }
    }
}