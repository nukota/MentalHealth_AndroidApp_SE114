package com.example.uplift.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.models.Testresults
import com.example.uplift.data.repository.TestresultsRepository
import kotlinx.coroutines.launch


class TestresultsViewModel (private val testresultsRepository: TestresultsRepository) : ViewModel() {
    private val _allTestresults = MutableLiveData<List<Testresults>>()
    val allTestresults: LiveData<List<Testresults>> get() = _allTestresults

    init {
        getAllTestresults()
    }
    private fun getAllTestresults() {
        viewModelScope.launch {
            testresultsRepository.getAllTestresults().observeForever { testresults ->
                _allTestresults.postValue(testresults)
            }
        }
    }
    fun insertTestresults(testresults: Testresults) {
        viewModelScope.launch {
            testresultsRepository.insertTestresults(testresults)
            getAllTestresults()
        }
    }
    fun updateTestresults(testresultsList: List<Testresults>) {
        viewModelScope.launch {
            testresultsRepository.updateTestresults(testresultsList)
            getAllTestresults()
        }
    }
    fun deleteAllTestresults() {
        viewModelScope.launch {
            testresultsRepository.deleteAllTestresults()
            _allTestresults.postValue(emptyList())
        }
    }
    fun deleteTestresultsById(testId:Int) {
        viewModelScope.launch {
            testresultsRepository.deleteTestresultsById(testId)
            getAllTestresults()
        }
    }
    fun getTestresultsById(testId: Int){
        viewModelScope.launch {
            val test = testresultsRepository.getTestresultsById(testId)
        }
    }
}