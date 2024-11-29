package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Test
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TestsRepository() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("tests")

    private val _tests = MutableLiveData<List<Test>>()
    val tests: LiveData<List<Test>> get() = _tests

    init {
        fetchTests()
    }

    fun getTestById(testId: Int): LiveData<Test?> {
        val test = _tests.value?.find { it.test_id == testId }
        val liveData = MutableLiveData<Test?>()
        liveData.value = test
        return liveData
    }

    private fun fetchTests() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val testList = mutableListOf<Test>()
                for (testSnapshot in snapshot.children) {
                    val test = testSnapshot.getValue(Test::class.java)
                    if (test != null) {
                        testList.add(test)
                    }
                }
                _tests.value = testList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TestRepository", "Error fetching tests", error.toException())
            }
        })
    }
}