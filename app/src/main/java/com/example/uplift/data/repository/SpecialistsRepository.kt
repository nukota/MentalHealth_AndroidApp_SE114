package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Specialist
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SpecialistsRepository() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("specialists")

    private val _specialists = MutableLiveData<List<Specialist>>()
    val specialists: LiveData<List<Specialist>> get() = _specialists

    init {
        fetchSpecialists()
    }

    fun getSpecialistById(specialistId: Int): LiveData<Specialist?> {
        val specialist = _specialists.value?.find { it.specialist_id == specialistId }
        val liveData = MutableLiveData<Specialist?>()
        liveData.value = specialist
        return liveData
    }

    private fun fetchSpecialists() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val specialistList = mutableListOf<Specialist>()
                for (specialistSnapshot in snapshot.children) {
                    val specialist = specialistSnapshot.getValue(Specialist::class.java)
                    if (specialist != null) {
                        specialistList.add(specialist)
                    }
                }
                _specialists.value = specialistList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SpecialistRepository", "Error fetching specialists", error.toException())
            }
        })
    }
}