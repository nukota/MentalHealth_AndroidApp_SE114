package com.example.uplift.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Test
import com.example.uplift.data.repository.TestsRepository


class ListTestsViewModel : ViewModel() {
    private val repository = TestsRepository()
    val tests: LiveData<List<Test>> = repository.tests

    fun getTestById(testId: Int): LiveData<Test?> {
        return repository.getTestById(testId)
    }

    fun getDurationByTestId(testId: Int): String {
        val questionCount = repository.getTestById(testId).value?.question_count ?: 0
        val totalSeconds = questionCount * 6
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return "$minutes min $seconds sec"
    }
}