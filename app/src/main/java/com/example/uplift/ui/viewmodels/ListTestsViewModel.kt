package com.example.uplift.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.models.Tests
import com.example.uplift.logic.repository.TestsRepository
import kotlinx.coroutines.launch


class ListTestsViewModel (application: Application) : AndroidViewModel(application) {
    private val testsRepository: TestsRepository
    private val allTests: MutableLiveData<List<Tests>> = MutableLiveData()

    init {
        val testsDao = TestsDatabase.getDatabase(application).testsDao()
        testsRepository = TestsRepository(testsDao)
        loadAllTests()
    }

    fun getAllTests(): LiveData<List<Tests>> {
        return allTests
    }

    private fun loadAllTests() {
        viewModelScope.launch {
            val tests = testsRepository.getAllTests()
            allTests.postValue(tests)
        }
    }

    fun deleteAllTests() {
        viewModelScope.launch {
            testsRepository.deleteAllTests()
            loadAllTests()
        }
    }
}