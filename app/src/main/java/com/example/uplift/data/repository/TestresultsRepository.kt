package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Testresult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TestresultsRepository() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("testresults")

    private val _testresults = MutableLiveData<List<Testresult>>()
    val testresults: LiveData<List<Testresult>> get() = _testresults

    init {
        fetchTestresults()
    }

    fun getTestresultById(testresultId: Int): LiveData<Testresult?> {
        val testresult = _testresults.value?.find { it.result_id == testresultId }
        val liveData = MutableLiveData<Testresult?>()
        liveData.value = testresult
        return liveData
    }

    private fun fetchTestresults() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val testresultList = mutableListOf<Testresult>()
                for (testresultSnapshot in snapshot.children) {
                    val testresult = testresultSnapshot.getValue(Testresult::class.java)
                    if (testresult != null) {
                        testresultList.add(testresult)
                    }
                }
                _testresults.value = testresultList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TestresultRepository", "Error fetching test results", error.toException())
            }
        })
    }
}