package com.example.uplift.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.models.Specialists
import com.example.uplift.data.models.Testresults
import com.example.uplift.data.models.Tests
import com.example.uplift.logic.dao.SpecialistsDao
import com.example.uplift.logic.repository.TestsRepository
import kotlinx.coroutines.launch


class SpecialistsViewModel (private val specialistsDao: SpecialistsDao) : ViewModel() {
    private val _allSpecialists = MutableLiveData<List<Specialists>>()
    val allSpecialists: LiveData<List<Specialists>> get() = _allSpecialists

    init {
        getAllSpecialists()
    }
    private fun getAllSpecialists() {
        viewModelScope.launch {
            specialistsDao.getAllSpecialists().observeForever { tests ->
                _allSpecialists.postValue(tests)
            }
        }
    }
    fun insertSpecialist(specialists: Specialists) {
        viewModelScope.launch {
            specialistsDao.insertSpecialist(specialists)
            getAllSpecialists()
        }
    }
    fun updateSpecialists(specialistsList: List<Specialists>) {
        viewModelScope.launch {
            specialistsDao.updateSpecialists(specialistsList)
            getAllSpecialists()
        }
    }
    fun deleteAllSpecialists() {
        viewModelScope.launch {
            specialistsDao.deleteAllSpecialists()
            _allSpecialists.postValue(emptyList())
        }
    }
    fun deleteSpecialistById(testId:Int) {
        viewModelScope.launch {
            specialistsDao.deleteSpecialistById(testId)
            getAllSpecialists()
        }
    }
    fun getSpecialistById(testId: Int){
        viewModelScope.launch {
            val test = specialistsDao.getSpecialistById(testId)
        }
    }
}