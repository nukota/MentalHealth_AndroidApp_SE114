package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.TestResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TestResultsRepository() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("testresults")

    private val _testResults = MutableLiveData<List<TestResult>>()
    val testResults: LiveData<List<TestResult>> get() = _testResults
    private val _scoreLiveData = MutableLiveData<Double>()

    init {
        fetchTestResults()
    }

    fun getTestResultByIdAndScore(testResultId: Int, score: Double): LiveData<TestResult?> {
        val resultLiveData = MutableLiveData<TestResult?>()

        val testResult = _testResults.value?.find {
            it.test_id == testResultId && score >= it.score_min && score <= it.score_max
        }
        resultLiveData.value = testResult
        return resultLiveData
    }

    private fun fetchTestResults() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val testResultList = mutableListOf<TestResult>()
                for (testResultSnapshot in snapshot.children) {
                    val testResult = testResultSnapshot.getValue(TestResult::class.java)
                    if (testResult != null) {
                        testResultList.add(testResult)
                    }
                }
                _testResults.value = testResultList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TestResultRepository", "Error fetching test results", error.toException())
            }
        })
    }
}