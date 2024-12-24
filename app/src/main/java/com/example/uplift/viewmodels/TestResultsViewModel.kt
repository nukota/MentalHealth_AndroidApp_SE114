package com.example.uplift.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.TestResult
import com.example.uplift.data.repository.TestResultsRepository

class TestResultsViewModel : ViewModel() {
    private val repository = TestResultsRepository()
    val testResults: LiveData<List<TestResult>> = repository.testResults

    private val _score = MutableLiveData(0.0)
    val score: LiveData<Double> = _score

    fun getTestResultByIdAndScore(testResultId: Int, score : Double): LiveData<TestResult?> {
        return repository.getTestResultByIdAndScore(testResultId, score)
    }
}