package com.example.uplift.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.models.Tests
import com.example.uplift.data.repository.TestsRepository
import kotlinx.coroutines.launch


class ListTestsViewModel (private val testsRepository: TestsRepository) : ViewModel() {
    private val _allTests = MutableLiveData<List<Tests>>()
    val allTests: LiveData<List<Tests>> get() = _allTests

    init {
        getAllTests()
    }
    private fun getAllTests() {
        viewModelScope.launch {
            testsRepository.getAllTests().observeForever { tests ->
                _allTests.postValue(tests)
            }
        }
    }
    fun insertTest(test: Tests) {
        viewModelScope.launch {
            testsRepository.insertTest(test)
            getAllTests()
        }
    }
    fun updateTests(testList: List<Tests>) {
        viewModelScope.launch {
            testsRepository.updateTests(testList)
            getAllTests()
        }
    }
    fun deleteAllTests() {
        viewModelScope.launch {
            testsRepository.deleteAllTests()
            _allTests.postValue(emptyList())
        }
    }
    fun deleteTestById(testId:Int) {
        viewModelScope.launch {
            testsRepository.deleteTestById(testId)
            getAllTests()
        }
    }
    fun getTestById(testId: Int){
        viewModelScope.launch {
            val test = testsRepository.getTestById(testId)
        }
    }
}